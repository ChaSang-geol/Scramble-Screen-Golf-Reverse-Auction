package payment.domain;

import lombok.Data;
import scrumble.domain.*;
import payment.infra.AbstractEvent;

@Data
public class PaymentCancellationRequested extends AbstractEvent {

    private Long gameReservationId;
    private Long biddingId;
    private String businessId;
    private Long biddingAmount;
    private String memberId;
    private String memberName;
    private String memberPhoneNum;
    // keep

}
