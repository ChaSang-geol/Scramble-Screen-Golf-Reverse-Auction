package scramble;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import scramble.config.kafka.KafkaProcessor;
import scramble.domain.Payment;
import scramble.domain.PaymentRepository;

@SpringBootApplication
@EnableBinding(KafkaProcessor.class)
@EnableFeignClients
public class PaymentApplication {

    public static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext =
            SpringApplication.run(PaymentApplication.class, args);
    }

    
}
