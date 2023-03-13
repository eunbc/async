package silverdragon.async.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AsyncRepository extends JpaRepository<MailSendRequestEntity,String> {
}
