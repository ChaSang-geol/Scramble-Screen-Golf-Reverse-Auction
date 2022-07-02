package scramble.domain;

import java.util.Date;
import java.util.Map;
import lombok.Data;
import scramble.domain.*;
import scramble.infra.AbstractEvent;

@Data
public class PaymentRequested extends AbstractEvent {

    private Long gameReservationId;
    private Long biddingId;
    private String businessId;
    private Long biddingAmount;
    private String memberId;
    private String memberName;
    private String memberPhoneNum;
    // keep

}
