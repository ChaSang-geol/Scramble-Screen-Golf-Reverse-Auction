package scramble.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import scramble.domain.*;

@RepositoryRestResource(collectionResourceRel = "notices", path = "notices")
public interface NoticeRepository
    extends JpaRepository<Notice, Long> {}
