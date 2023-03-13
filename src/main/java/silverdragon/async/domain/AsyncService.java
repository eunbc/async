package silverdragon.async.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AsyncService {

    private final AsyncRepository asyncRepository;
    public String sendMail() {
        //db에 row 추가 기본 ready
        //entity 객체 만들어서 값 넣어주면

        // 요청:{"receiver":["abc@naver.com", "def@naver.com"], "mailTitle":"~~", "mailBody":"~~"}

        //send mail 이부분이 비동기처리가 필요
        // 여기서 정상적으로 메일을 보내고 나면 success로 update
        if(false){
            return "fail";
        }
        return "success";
    }

    @Async
    public void send(){
        //running

        //logic


        //success fail
    }
}
