package scramble.common;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import scramble.MemberApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = { MemberApplication.class })
public class CucumberSpingConfiguration {}
