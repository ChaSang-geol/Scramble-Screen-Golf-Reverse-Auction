package reservation.infra;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reservation.domain.*;

@RestController
@RequestMapping(value = "/reservations")
@Transactional
public class ReservationController {

    @Autowired
    ReservationRepository reservationRepository;
    // keep
}
