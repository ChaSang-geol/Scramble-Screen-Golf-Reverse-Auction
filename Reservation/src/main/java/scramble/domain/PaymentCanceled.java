package scramble.domain;

import java.util.Date;
import java.util.Map;
import lombok.Data;
import scramble.domain.*;
import scramble.infra.AbstractEvent;

@Data
public class PaymentCanceled extends AbstractEvent {

    private Long paymentId;
    private Long gameReservationId;
    private String paymentStatus;
    // keep

}
