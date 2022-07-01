package scramble.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import scramble.PaymentApplication;
import scramble.domain.Paid;
import scramble.domain.PaymentCanceled;

@Entity
@Table(name = "Payment_table")
@Data
public class Payment {

    private Long paymentId;

    private Long gameReservationId;

    private String paymentMethod;

    private Double paymentAmount;

    private String paymentStatus;

    private String paymentAccount;

    @PostPersist
    public void onPostPersist() {
        Paid paid = new Paid(this);
        paid.publishAfterCommit();

        PaymentCanceled paymentCanceled = new PaymentCanceled(this);
        paymentCanceled.publishAfterCommit();
    }

    public static PaymentRepository repository() {
        PaymentRepository paymentRepository = PaymentApplication.applicationContext.getBean(
            PaymentRepository.class
        );
        return paymentRepository;
    }
}
