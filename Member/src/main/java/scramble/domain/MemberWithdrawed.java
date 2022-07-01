package scramble.domain;

import java.util.Date;
import lombok.Data;
import scramble.domain.*;
import scramble.infra.AbstractEvent;

@Data
public class MemberWithdrawed extends AbstractEvent {

    private String memberId;
    private String status;

    public MemberWithdrawed(Member aggregate) {
        super(aggregate);
    }

    public MemberWithdrawed() {
        super();
    }
    // keep

}
