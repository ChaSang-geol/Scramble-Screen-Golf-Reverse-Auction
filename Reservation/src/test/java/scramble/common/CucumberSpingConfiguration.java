package reservation.common;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import reservation.ReservationApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = { ReservationApplication.class })
public class CucumberSpingConfiguration {}
