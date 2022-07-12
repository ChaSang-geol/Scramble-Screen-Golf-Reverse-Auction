package notification.domain;

import java.util.Date;
import lombok.Data;
import scrumble.domain.*;
import notification.infra.AbstractEvent;

@Data
public class ReservationCancelSent extends AbstractEvent {

    private Long notiId;
    private String receiverPhoneNum;
    private String content;
    private String senderPhoneNum;
    private Date sendDate;
    private String notiFlag;
    private Long gameReservationId;

    public ReservationCancelSent(Notice aggregate) {
        super(aggregate);
    }

    public ReservationCancelSent() {
        super();
    }
    // keep

}
