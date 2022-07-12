package reservation.domain;

import lombok.Data;
import reservation.infra.AbstractEvent;

@Data
public class ReservationCancellationRequestReceived extends AbstractEvent {

    private Long id;

    public ReservationCancellationRequestReceived(Reservation aggregate) {
        super(aggregate);
    }

    public ReservationCancellationRequestReceived() {
        super();
    }
    // keep

}
