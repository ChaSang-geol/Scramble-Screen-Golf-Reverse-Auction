package scramble.domain;

import java.util.Date;
import lombok.Data;
import scramble.infra.AbstractEvent;

@Data
public class BusinessRegistRejectNotiSent extends AbstractEvent {

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

    public BusinessRegistRejectNotiSent(Notice aggregate) {
        super(aggregate);
    }

    public BusinessRegistRejectNotiSent() {
        super();
    }
    // keep

}
