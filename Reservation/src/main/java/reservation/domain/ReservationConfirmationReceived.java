package reservation.domain;

import lombok.Data;
import reservation.infra.AbstractEvent;

@Data
public class ReservationConfirmationReceived extends AbstractEvent {

    private Long id;

    public ReservationConfirmationReceived(Reservation aggregate) {
        super(aggregate);
    }

    public ReservationConfirmationReceived() {
        super();
    }
    // keep

}
