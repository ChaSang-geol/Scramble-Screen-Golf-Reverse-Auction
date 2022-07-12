package member.domain;

import lombok.Data;
import member.infra.AbstractEvent;

@Data
public class MemberUpdated extends AbstractEvent {

    private String memberId;
    private String memberName;
    private String phoneNumber;
    private String status;
    private String role;

    public MemberUpdated(Member aggregate) {
        super(aggregate);
    }

    public MemberUpdated() {
        super();
    }
    // keep

}
