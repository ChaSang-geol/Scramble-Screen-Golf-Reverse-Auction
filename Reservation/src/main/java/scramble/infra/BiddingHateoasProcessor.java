package scramble.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import scramble.domain.*;

@Component
public class BiddingHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Bidding>> {

    @Override
    public EntityModel<Bidding> process(EntityModel<Bidding> model) {
        return model;
    }
}
