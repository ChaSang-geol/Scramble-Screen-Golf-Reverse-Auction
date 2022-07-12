package member.domain;

import java.util.Optional;

public interface MemberFindService {
  Optional<Member> findById(long id);
  Optional<Member> findByMemberId(String memberId);
}
