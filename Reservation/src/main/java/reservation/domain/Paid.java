package reservation.domain;

import lombok.Data;
import reservation.infra.AbstractEvent;

@Data
public class Paid extends AbstractEvent {

    private Long paymentId;
    private Long gameReservationId;
    private String paymentMethod;
    private Double paymentAmount;
    private String paymentStatus;
    private String paymentAccount;
    // keep

}
