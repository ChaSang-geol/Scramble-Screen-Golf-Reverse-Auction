package reservation.domain;

import java.util.Date;
import javax.persistence.*;
import reservation.ReservationApplication;

@Entity
@Table(name = "Reservation_table")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long gameReservationId;

    private String memberId;

    private Long biddingId;

    private Integer memberCount;

    private Date reservationTime;

    private String location;

    private String memberName;

    private String memberPhoneNum;

    private String reservationStatus;

    public Long getGameReservationId() {
        return gameReservationId;
    }

    public void setGameReservationId(Long gameReservationId) {
        this.gameReservationId = gameReservationId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Long getBiddingId() {
        return biddingId;
    }

    public void setBiddingId(Long biddingId) {
        this.biddingId = biddingId;
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    public Date getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Date reservationTime) {
        this.reservationTime = reservationTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberPhoneNum() {
        return memberPhoneNum;
    }

    public void setMemberPhoneNum(String memberPhoneNum) {
        this.memberPhoneNum = memberPhoneNum;
    }

    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    @PostPersist
    public void onPostPersist() {
        ReservationCancellationRequestReceived reservationCancellationRequestReceived = new ReservationCancellationRequestReceived(
            this
        );
        reservationCancellationRequestReceived.publishAfterCommit();

        SelectedBiddingBusiness selectedBiddingBusiness = new SelectedBiddingBusiness(
            this
        );
        selectedBiddingBusiness.publishAfterCommit();

        ReservationConfirmationReceived reservationConfirmationReceived = new ReservationConfirmationReceived(
            this
        );
        reservationConfirmationReceived.publishAfterCommit();
    }

    public static ReservationRepository repository() {
        ReservationRepository reservationRepository = ReservationApplication.applicationContext.getBean(
            ReservationRepository.class
        );
        return reservationRepository;
    }

    public static void paymentCanceled(PaymentCanceled paymentCanceled) {
        /** Example 1:  new item 
        Reservation reservation = new Reservation();
        repository().save(reservation);

        ReservationCancellationRequestReceived reservationCancellationRequestReceived = new ReservationCancellationRequestReceived(reservation);
        reservationCancellationRequestReceived.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(paymentCanceled.get???()).ifPresent(reservation->{
            
            reservation // do something
            repository().save(reservation);

            ReservationCancellationRequestReceived reservationCancellationRequestReceived = new ReservationCancellationRequestReceived(reservation);
            reservationCancellationRequestReceived.publishAfterCommit();

         });
        */

    }

    public static void paymentCompleted(Paid paid) {
        /** Example 1:  new item 
        Reservation reservation = new Reservation();
        repository().save(reservation);

        ReservationConfirmationReceived reservationConfirmationReceived = new ReservationConfirmationReceived(reservation);
        reservationConfirmationReceived.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(paid.get???()).ifPresent(reservation->{
            
            reservation // do something
            repository().save(reservation);

            ReservationConfirmationReceived reservationConfirmationReceived = new ReservationConfirmationReceived(reservation);
            reservationConfirmationReceived.publishAfterCommit();

         });
        */

    }
}
