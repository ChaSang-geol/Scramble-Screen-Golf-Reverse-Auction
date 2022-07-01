package scramble.domain;

import java.util.Date;
import lombok.Data;
import scramble.domain.*;
import scramble.infra.AbstractEvent;

@Data
public class MemberUpdated extends AbstractEvent {

    private String memberId;
    private String password;
    private String memberName;
    private String phoneNumber;

    public MemberUpdated(Member aggregate) {
        super(aggregate);
    }

    public MemberUpdated() {
        super();
    }
    // keep

}
