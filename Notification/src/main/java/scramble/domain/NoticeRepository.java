package scramble.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import scramble.domain.*;

@RepositoryRestResource(collectionResourceRel = "notices", path = "notices")
public interface NoticeRepository
    extends PagingAndSortingRepository<Notice, Long> {}
