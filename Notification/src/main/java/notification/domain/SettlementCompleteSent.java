package notification.domain;

import java.util.Date;
import lombok.Data;
import scrumble.domain.*;
import notification.infra.AbstractEvent;

@Data
public class SettlementCompleteSent extends AbstractEvent {

    private Long notiId;
    private String receiverPhoneNum;
    private String content;
    private String senderPhoneNum;
    private Date sendDate;
    private String notiFlag;
    private Long gameReservationId;
    private Long biddingId;
    private Long settleid;
    private Long paymentId;

    public SettlementCompleteSent(Notice aggregate) {
        super(aggregate);
    }

    public SettlementCompleteSent() {
        super();
    }
    // keep

}
