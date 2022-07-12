package notification.domain;

import java.util.Date;
import lombok.Data;
import notification.infra.AbstractEvent;
import scrumble.domain.*;

@Data
public class BusinessRegistCompleteNotiSent extends AbstractEvent {

    private Long notiId;
    private String receiverPhoneNum;
    private String content;
    private String senderPhoneNum;
    private Date sendDate;
    private String notiFlag;

    public BusinessRegistCompleteNotiSent(Notice aggregate) {
        super(aggregate);
    }

    public BusinessRegistCompleteNotiSent() {
        super();
    }
    // keep

}
