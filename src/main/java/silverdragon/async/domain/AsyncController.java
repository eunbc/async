package silverdragon.async.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AsyncController {
    private final AsyncService asyncService;

    @PostMapping("/sendMail")
    public String sendMail() {
        asyncService.sendMail();
        return "mail";
    }
}