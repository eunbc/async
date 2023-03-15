package silverdragon.async.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import silverdragon.async.AsyncEnum;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class AsyncService {
    private final AsyncRepository asyncRepository;
    private final JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "hia5314@gmail.com";

    public String sendMail(MailSendRequestDto mailSendRequestDto) {
        MailSendRequestEntity entity = asyncRepository.save(dtoToEntity(mailSendRequestDto).orElseThrow());

        send(entity,mailSendRequestDto.getMailReceiver());

        return "requested";
    }

    @Async
    public void send(MailSendRequestEntity entity, List<String> receiver) {
        entity.setStatus(AsyncEnum.RUNNING.getValue());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(FROM_ADDRESS);
        message.setTo(receiver.toArray(new String[0]));
        message.setSubject(entity.getMailTitle());
        message.setText(entity.getMailBody());

        try{
            mailSender.send(message);
        } catch (MailException e) {
            log.error(e);
            entity.setStatus(AsyncEnum.FAILED.getValue());
        }

        entity.setStatus(AsyncEnum.SUCCESS.getValue());
    }

    public Optional<MailSendRequestEntity> dtoToEntity(MailSendRequestDto dto) {
        String uuid = UUID.randomUUID().toString();
        String receiver = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            receiver = mapper.writeValueAsString(dto.getMailReceiver());
        } catch (JsonProcessingException e) {
            log.error(e);
            return Optional.empty();
        }

        return Optional.of(MailSendRequestEntity.builder()
                .uuid(uuid)
                .mailTitle(dto.getMailTitle())
                .mailBody(dto.getMailBody())
                .mailReceiver(receiver)
                .status(AsyncEnum.READY.getValue())
                .build());
    }
}
