package scramble.jwt.domain;

import java.io.Serializable;

public interface JWTPayload extends Serializable {

    long getUserId();
    boolean isExpired();

}
