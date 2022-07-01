package scramble.domain;

import java.util.Date;
import lombok.Data;
import scramble.domain.*;
import scramble.infra.AbstractEvent;

@Data
public class MemberLoggedIn extends AbstractEvent {

    private String memberId;
    private String password;

    public MemberLoggedIn(Member aggregate) {
        super(aggregate);
    }

    public MemberLoggedIn() {
        super();
    }
    // keep

}
