package scramble.jwt.domain;

import scramble.domain.Member;

public interface JWTSerializer {

    String jwtFromUser(Member member);

}
