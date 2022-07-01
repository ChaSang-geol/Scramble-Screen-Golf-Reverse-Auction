package scramble.domain;

import java.util.Date;
import lombok.Data;
import scramble.domain.*;
import scramble.infra.AbstractEvent;

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
