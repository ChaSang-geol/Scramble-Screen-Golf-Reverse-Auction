package scramble.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import scramble.domain.*;

@RepositoryRestResource(collectionResourceRel = "biddings", path = "biddings")
public interface BiddingRepository
    extends PagingAndSortingRepository<Bidding, Long> {}
