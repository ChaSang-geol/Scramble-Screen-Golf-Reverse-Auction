package notification.domain;

import java.util.Date;
import lombok.Data;
import notification.infra.AbstractEvent;

@Data
public class SettlementRejectSent extends AbstractEvent {

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

    public SettlementRejectSent(Notice aggregate) {
        super(aggregate);
    }

    public SettlementRejectSent() {
        super();
    }
    // keep

}
