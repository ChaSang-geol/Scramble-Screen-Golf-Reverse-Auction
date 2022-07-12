package member.domain;

import javax.persistence.Id;

import lombok.Data;

@Data
public class RequestUpdateMember {

  @Id
  private Long id;
  private String memberId;
  private String password;
  private String memberName;
  private String phoneNumber;
  private String status;
  private String role;
}