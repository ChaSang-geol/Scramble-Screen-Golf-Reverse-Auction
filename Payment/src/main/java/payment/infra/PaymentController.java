package payment.infra;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import payment.domain.Payment;
import payment.domain.PaymentRepository;
import scrumble.domain.*;

@RestController
@RequestMapping(value = "/payments")
@Transactional
public class PaymentController {

    @Autowired
    PaymentRepository paymentRepository;
    // keep
    
	@PutMapping(path="/{paymentId}/cancel")
	public String cancelPayment(@PathVariable(value = "paymentId") Long paymentId){

		Payment thePayment = paymentRepository.findById(paymentId).get();

		thePayment.cancelPayment();

		paymentRepository.save(thePayment);

		return "결제를 취소하였습니다.";
	}

    @PutMapping(path="/{paymentId}/complete")
	public String completePayment(@PathVariable(value = "paymentId") Long paymentId){

		Payment thePayment = paymentRepository.findById(paymentId).get();

		thePayment.completePayment();

		paymentRepository.save(thePayment);

		return "결제를 완료하였습니다.";
	}
}
