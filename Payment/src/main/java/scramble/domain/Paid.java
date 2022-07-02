package scramble.domain;

import java.util.Date;
import lombok.Data;
import scramble.domain.*;
import scramble.infra.AbstractEvent;
/*
 * 결제 완료가 되었을 때 값 세팅
 * 
 */
@Data
public class Paid extends AbstractEvent {

    private Long paymentId;
    private Long gameReservationId;
    //private String paymentMethod;
    PaymentMethod paymentMethod;
    private Long paymentAmount;
    //private String paymentStatus;
    PaymentStatus paymentStatus;
    private String paymentAccount;
    private String memberId;
    private String memberName;
    private String memberPhoneNum;

    public Paid(Payment aggregate) {
        super(aggregate);
    }

    public Paid() {
        super();
    }
    // keep

   
}
