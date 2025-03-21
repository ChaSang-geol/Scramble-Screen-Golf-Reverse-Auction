package notification.domain;

import java.time.LocalDateTime;
import javax.persistence.*;
import notification.NotificationApplication;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "Notice_table")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long notiId;

    private String receiverPhoneNum;

    private String content;

    private String senderPhoneNum;

    private LocalDateTime sendDate;

    // private String notiFlag;
    @Embedded
    private MessageFlag notiFlag;

    private Long gameReservationId;

    private Long biddingId;

    private Long settleid;

    private Long paymentId;

    private String memberName;

    public MessageFlag getNotiFlag() {
        return notiFlag;
    }

    public void setNotiFlag(MessageFlag notiFlag) {
        this.notiFlag = notiFlag;
    }

    public Long getNotiId() {
        return notiId;
    }

    public void setNotiId(Long notiId) {
        this.notiId = notiId;
    }

    public String getReceiverPhoneNum() {
        return receiverPhoneNum;
    }

    public void setReceiverPhoneNum(String receiverPhoneNum) {
        this.receiverPhoneNum = receiverPhoneNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSenderPhoneNum() {
        return senderPhoneNum;
    }

    public void setSenderPhoneNum(String senderPhoneNum) {
        this.senderPhoneNum = senderPhoneNum;
    }

    public LocalDateTime getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDateTime sendDate) {
        this.sendDate = sendDate;
    }

    public Long getGameReservationId() {
        return gameReservationId;
    }

    public void setGameReservationId(Long gameReservationId) {
        this.gameReservationId = gameReservationId;
    }

    public Long getBiddingId() {
        return biddingId;
    }

    public void setBiddingId(Long biddingId) {
        this.biddingId = biddingId;
    }

    public Long getSettleid() {
        return settleid;
    }

    public void setSettleid(Long settleid) {
        this.settleid = settleid;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

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
