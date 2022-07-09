package scramble.jwt.domain;

import org.springframework.context.annotation.Bean;

public interface JWTDeserializer {

    @Bean
    JWTPayload jwtPayloadFromJWT(String jwtToken);

}
