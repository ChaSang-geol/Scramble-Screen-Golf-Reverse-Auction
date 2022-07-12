package reservation.domain;

import lombok.Data;
import reservation.infra.AbstractEvent;

@Data
public class PaymentCanceled extends AbstractEvent {

    private Long paymentId;
    private Long gameReservationId;
    private String paymentStatus;
    // keep

}
