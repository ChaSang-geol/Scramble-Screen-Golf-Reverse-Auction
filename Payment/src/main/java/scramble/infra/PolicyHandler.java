package scramble.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import scramble.config.kafka.KafkaProcessor;
import scramble.domain.*;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    PaymentRepository paymentRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentRequested_RequestPayment(
        @Payload PaymentRequested paymentRequested
    ) {
        if (!paymentRequested.validate()) return;
        PaymentRequested event = paymentRequested;
        System.out.println(
            "\n\n##### listener RequestPayment : " +
            paymentRequested.toJson() +
            "\n\n"
        );

        // Comments //
        //결제 요청을 받음
        //

        // Sample Logic //
        Payment.requestPayment(event);
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentCancellationRequested_RequestCancelPayment(
        @Payload PaymentCancellationRequested paymentCancellationRequested
    ) {
        if (!paymentCancellationRequested.validate()) return;
        PaymentCancellationRequested event = paymentCancellationRequested;
        System.out.println(
            "\n\n##### listener RequestCancelPayment : " +
            paymentCancellationRequested.toJson() +
            "\n\n"
        );

        // Comments //
        //결제 취소요청 받음

        // Sample Logic //
        Payment.requestCancelPayment(event);
    }
    // keep

}
