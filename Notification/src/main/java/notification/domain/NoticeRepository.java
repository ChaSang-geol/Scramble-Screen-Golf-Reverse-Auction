package notification.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import scrumble.domain.*;

@RepositoryRestResource(collectionResourceRel = "notices", path = "notices")
public interface NoticeRepository
    extends JpaRepository<Notice, Long> {}
