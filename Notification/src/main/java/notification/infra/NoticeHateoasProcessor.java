package notification.infra;

import notification.domain.Notice;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import scrumble.domain.*;

@Component
public class NoticeHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Notice>> {

    @Override
    public EntityModel<Notice> process(EntityModel<Notice> model) {
        return model;
    }
}
