package scramble.domain;

import java.util.Date;
import lombok.Data;
import scramble.domain.*;
import scramble.infra.AbstractEvent;

@Data
public class ReservationConfirmationSent extends AbstractEvent {

    private Long notiId;
    private String receiverPhoneNum;
    private String content;
    private String senderPhoneNum;
    private Date sendDate;
    private String notiFlag;
    private Long gameReservationId;
    private Long biddingId;

    public ReservationConfirmationSent(Notice aggregate) {
        super(aggregate);
    }

    public ReservationConfirmationSent() {
        super();
    }
    // keep

}
