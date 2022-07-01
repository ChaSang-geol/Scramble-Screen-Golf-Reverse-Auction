package scramble.domain;

import java.util.Date;
import lombok.Data;
import scramble.domain.*;
import scramble.infra.AbstractEvent;

@Data
public class SettlementProcessingSent extends AbstractEvent {

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

    public SettlementProcessingSent(Notice aggregate) {
        super(aggregate);
    }

    public SettlementProcessingSent() {
        super();
    }
    // keep

}
