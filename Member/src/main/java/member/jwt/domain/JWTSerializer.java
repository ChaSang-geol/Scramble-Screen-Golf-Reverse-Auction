package member.jwt.domain;

import member.domain.Member;

public interface JWTSerializer {

    String jwtFromUser(Member member);

}
