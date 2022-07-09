package scramble.domain.jwt;

import scramble.domain.Member;

public interface JWTSerializer {

    String jwtFromUser(Member member);

}
