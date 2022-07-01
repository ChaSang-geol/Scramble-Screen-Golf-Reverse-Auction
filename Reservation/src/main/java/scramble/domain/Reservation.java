package scramble.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import scramble.ReservationApplication;
import scramble.domain.ReservationCancellationRequestReceived;
import scramble.domain.ReservationConfirmationReceived;
import scramble.domain.SelectedBiddingBusiness;

@Entity
@Table(name = "Reservation_table")
@Data
public class Reservation {

    private Long gameReservationId;

    private String memberId;

    private Long biddingId;

    private Integer memberCount;

    private Date reservationTime;

    private String location;

    private String memberName;

    private String memberPhoneNum;

    private String reservationStatus;

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
