package scramble.domain;

import java.util.Date;
import lombok.Data;
import scramble.domain.*;
import scramble.infra.AbstractEvent;

@Data
public class MemberJoined extends AbstractEvent {

    private String memberId;
    private String memberName;
    private String phoneNumber;
    private String status;
    private String role;

    public MemberJoined(Member aggregate) {
        super(aggregate);
    }

    public MemberJoined() {
        super();
    }
    // keep

}
