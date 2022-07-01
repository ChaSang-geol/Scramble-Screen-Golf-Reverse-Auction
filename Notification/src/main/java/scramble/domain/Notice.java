package scramble.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import scramble.NotificationApplication;
import scramble.domain.BusinessRegistCompleteNotiSent;
import scramble.domain.ReservationCancelSent;
import scramble.domain.ReservationConfirmationSent;
import scramble.domain.SettlementProcessingSent;

@Entity
@Table(name = "Notice_table")
@Data
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @PostPersist
    public void onPostPersist() {
        SettlementProcessingSent settlementProcessingSent = new SettlementProcessingSent(
            this
        );
        settlementProcessingSent.publishAfterCommit();

        BusinessRegistCompleteNotiSent businessRegistCompleteNotiSent = new BusinessRegistCompleteNotiSent(
            this
        );
        businessRegistCompleteNotiSent.publishAfterCommit();

        ReservationConfirmationSent reservationConfirmationSent = new ReservationConfirmationSent(
            this
        );
        reservationConfirmationSent.publishAfterCommit();

        ReservationCancelSent reservationCancelSent = new ReservationCancelSent(
            this
        );
        reservationCancelSent.publishAfterCommit();
    }

    public static NoticeRepository repository() {
        NoticeRepository noticeRepository = NotificationApplication.applicationContext.getBean(
            NoticeRepository.class
        );
        return noticeRepository;
    }
}
