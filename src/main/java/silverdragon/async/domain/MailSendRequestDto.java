package silverdragon.async.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class MailSendRequestDto {
    private String mailTitle;
    private String mailBody;
    private List<String> mailReceiver;

}
