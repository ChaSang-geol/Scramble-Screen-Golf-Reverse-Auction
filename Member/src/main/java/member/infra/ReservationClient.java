package member.infra;

import member.domain.Resevation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("reservations")
public interface ReservationClient {
  @RequestMapping(method = RequestMethod.GET, value = "/reservations")
  List<Resevation> getReservationForMemberId(@RequestParam("memberid") String memberId);
}
/*  FeignClient sample */
/*
@FeignClient("reservations")
public interface StoreClient {
    @RequestMapping(method = RequestMethod.GET, value = "/stores")
    List<Store> getStores();

    @RequestMapping(method = RequestMethod.POST, value = "/stores/{storeId}", consumes = "application/json")
    Store update(@PathVariable("storeId") Long storeId, Store store);
}
*/