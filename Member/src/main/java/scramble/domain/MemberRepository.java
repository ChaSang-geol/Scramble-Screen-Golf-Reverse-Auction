package scramble.domain;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "members", path = "members")
public interface MemberRepository
    extends PagingAndSortingRepository<Member, Long> {
        Optional<Member> findByMemberId(String memberId);
    }
