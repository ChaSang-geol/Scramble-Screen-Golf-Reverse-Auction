package member.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class Resevation {
  private Long gameReservationId;

  private String memberId;

  private Long biddingId;

  private Integer memberCount;

  private Date reservationTime;

  private String location;

  private String memberName;

  private String memberPhoneNum;

  private String reservationStatus;
}
