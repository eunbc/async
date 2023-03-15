package silverdragon.async.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AsyncService {

    private final AsyncRepository asyncRepository;
    private final JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "hia5314@gmail.com";

    public String sendMail(MailSendRequestDto mailSendRequestDto) {
        MailSendRequestEntity entity = asyncRepository.save(dtoToEntity(mailSendRequestDto).orElseThrow());

        // 요청:{"receiver":["abc@naver.com", "def@naver.com"], "mailTitle":"~~", "mailBody":"~~"}

        //send mail 이부분이 비동기처리가 필요
        // 여기서 정상적으로 메일을 보내고 나면 success로 update
        if(false){
            return "fail";
        }
        return "success";
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
}
