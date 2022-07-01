package scramble.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import scramble.domain.*;

@RepositoryRestResource(collectionResourceRel = "members", path = "members")
public interface MemberRepository
    extends PagingAndSortingRepository<Member, String> {}
