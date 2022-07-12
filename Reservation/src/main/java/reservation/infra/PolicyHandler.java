package reservation.infra;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import reservation.config.kafka.KafkaProcessor;
import reservation.domain.*;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    BiddingRepository biddingRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentCanceled_PaymentCanceled(
        @Payload PaymentCanceled paymentCanceled
    ) {
        if (!paymentCanceled.validate()) return;
        PaymentCanceled event = paymentCanceled;
        System.out.println(
            "\n\n##### listener PaymentCanceled : " +
            paymentCanceled.toJson() +
            "\n\n"
        );

        // Sample Logic //
        Reservation.paymentCanceled(event);
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaid_PaymentCompleted(@Payload Paid paid) {
        if (!paid.validate()) return;
        Paid event = paid;
        System.out.println(
            "\n\n##### listener PaymentCompleted : " + paid.toJson() + "\n\n"
        );

        // Sample Logic //
        Reservation.paymentCompleted(event);
    }
    // keep

}
