package scramble.domain;

import java.time.LocalDateTime;

import lombok.Data;
import scramble.infra.AbstractEvent;

@Data
public class SendMessage extends AbstractEvent {
  private Long notiId;

    private String receiverPhoneNum;

    private String content;

    private String senderPhoneNum;

    private LocalDateTime sendDate;

    private String notiFlag;

    private Long gameReservationId;

    private Long biddingId;

    private Long settleid;

    private Long paymentId;

    private String memberName;
}
