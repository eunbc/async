package silverdragon.async.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AsyncController {
    private final AsyncService asyncService;

    @PostMapping("/send-mail")
    public String sendMail(MailSendRequestDto mailSendRequestDto) {
        asyncService.sendMail(mailSendRequestDto);
        return "mail";
    }
}