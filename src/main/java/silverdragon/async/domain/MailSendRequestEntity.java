package silverdragon.async.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class MailSendRequestEntity {
    @Id
    private String uuid;
    private String mailTitle;
    private String mailBody;
    private String mailReceiver;
    private String status;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

}
