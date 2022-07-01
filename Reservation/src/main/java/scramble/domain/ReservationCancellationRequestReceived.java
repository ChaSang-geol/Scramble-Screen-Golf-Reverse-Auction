package scramble.domain;

import java.util.Date;
import lombok.Data;
import scramble.domain.*;
import scramble.infra.AbstractEvent;

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
