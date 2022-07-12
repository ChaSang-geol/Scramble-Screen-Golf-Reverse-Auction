package reservation.domain;

import lombok.Data;
import reservation.infra.AbstractEvent;

@Data
public class SelectedBiddingBusiness extends AbstractEvent {

    private Long gameReservationId;
    private String memberId;
    private Long biddingId;
    private String memberName;
    private String memberPhoneNum;
    private Long biddingAmount;

    public SelectedBiddingBusiness(Reservation aggregate) {
        super(aggregate);
    }

    public SelectedBiddingBusiness() {
        super();
    }
    // keep

}
