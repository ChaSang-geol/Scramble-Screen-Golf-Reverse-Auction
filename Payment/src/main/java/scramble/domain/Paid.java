package scramble.domain;

import java.util.Date;
import lombok.Data;
import scramble.domain.*;
import scramble.infra.AbstractEvent;

@Data
public class Paid extends AbstractEvent {

    private Long paymentId;
    private Long gameReservationId;
    private String paymentMethod;
    private Double paymentAmount;
    private String paymentStatus;
    private String paymentAccount;

    public Paid(Payment aggregate) {
        super(aggregate);
    }

    public Paid() {
        super();
    }
    // keep

}
