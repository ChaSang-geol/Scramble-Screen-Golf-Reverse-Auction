package scramble.domain;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder.Case;

import org.springframework.beans.BeanUtils;

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

    private LocalDateTime sendDate;

    // private String notiFlag;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255)", name="noti_flag")
    private MessageFlag notiFlag;

    private Long gameReservationId;

    private Long biddingId;

    private Long settleid;

    private Long paymentId;

    private String memberName;

    @PostPersist
    public void onPostPersist() {
        String mflag = this.getNotiFlag().toString();
        switch (mflag) {

        case "SETTLREQST" : //정산요청
            SettlementProcessingSent settlementProcessingSent = new SettlementProcessingSent(
                    this);
            BeanUtils.copyProperties(this, settlementProcessingSent);
            settlementProcessingSent.publishAfterCommit();
            break;

        case "SETTLECOMPLETE": // 정산완료
            SettlementCompleteSent settlementCompleteSent = new SettlementCompleteSent(
                    this);
            BeanUtils.copyProperties(this, settlementCompleteSent);
            settlementCompleteSent.publishAfterCommit();
            break;

        case "SETTLEREJECT":   // 정산거부

            SettlementRejectSent settlementRejectSent = new SettlementRejectSent(
                    this);
            BeanUtils.copyProperties(this, settlementRejectSent);
            settlementRejectSent.publishAfterCommit();
            break;

        case "BIZJOINCOMPLETE" : // 사업자등록완료

            BusinessRegistCompleteNotiSent businessRegistCompleteNotiSent = new BusinessRegistCompleteNotiSent(
                    this);
            BeanUtils.copyProperties(this, businessRegistCompleteNotiSent);
            businessRegistCompleteNotiSent.publishAfterCommit();
            break;
        case "BIZJOINREJECT": // 사업자등록거부
            BusinessRegistRejectNotiSent businessRegistRejectNotiSent = new BusinessRegistRejectNotiSent(
                    this);
            BeanUtils.copyProperties(this, businessRegistRejectNotiSent);
            businessRegistRejectNotiSent.publishAfterCommit();

            break;
        case "RESCONFIRM" : // 예약확정

            ReservationConfirmationSent reservationConfirmationSent = new ReservationConfirmationSent(
                    this);
            BeanUtils.copyProperties(this, reservationConfirmationSent);
            reservationConfirmationSent.publishAfterCommit();
            break;
        case "RESCANCEL":  // 예약취소
            ReservationCancelSent reservationCancelSent = new ReservationCancelSent(
                    this);
            BeanUtils.copyProperties(this, reservationCancelSent);
            reservationCancelSent.publishAfterCommit();
            break;

        default :
            break;

        }

    }

    // 알람송신 요청 저장
    public static void saveSendMessage(SendMessage sendMessage) {
        LocalDateTime now = LocalDateTime.now();

        Notice notice = new Notice();
        BeanUtils.copyProperties(sendMessage, notice);
        sendMessage.setSendDate(now);
        repository().save(notice);
    }


    public static NoticeRepository repository() {
        NoticeRepository noticeRepository = NotificationApplication.applicationContext.getBean(
                NoticeRepository.class);
        return noticeRepository;
    }
}
