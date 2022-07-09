package scramble.domain;

import java.util.Date;
import lombok.Data;
import scramble.domain.*;
import scramble.infra.AbstractEvent;

@Data
public class MemberWithdrawed extends AbstractEvent {

    private String memberId;   // 회원 ID
    private String memberName; // 이름
    private String phoneNumber; // 휴대전화번호
    private String status;      // 탈퇴 : N

    public MemberWithdrawed(Member aggregate) {
        super(aggregate);
    }

    public MemberWithdrawed() {
        super();
    }
    // keep

}
