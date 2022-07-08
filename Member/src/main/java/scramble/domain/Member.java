package scramble.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.persistence.metamodel.SetAttribute;

import lombok.Builder;
import lombok.Data;
import scramble.MemberApplication;
import scramble.domain.MemberJoined;
import scramble.domain.MemberLoggedIn;
import scramble.domain.MemberUpdated;
import scramble.domain.MemberWithdrawed;

@Entity
@Table(name = "Member_table")
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique=true)
    private String memberId; // login ID
    private String password; //
    private String memberName; // 이름
    private String phoneNumber; // 휴대전화번호
    private String status="Y"; // 상태 : Y 가입, N 탈퇴
    @Embedded
    //@Builder.Default
    Role role ;//= Role.MEMBER; // 회원의 구분 & 권한

    @PostPersist
    public void onPostPersist() {
        MemberJoined memberJoined = new MemberJoined(this);
        memberJoined.publishAfterCommit();

        MemberUpdated memberUpdated = new MemberUpdated(this);
        memberUpdated.publishAfterCommit();

        MemberWithdrawed memberWithdrawed = new MemberWithdrawed(this);
        memberWithdrawed.publishAfterCommit();

        MemberLoggedIn memberLoggedIn = new MemberLoggedIn(this);
        memberLoggedIn.publishAfterCommit();
    }

    public static MemberRepository repository() {
        MemberRepository memberRepository = MemberApplication.applicationContext.getBean(
            MemberRepository.class
        );
        return memberRepository;
    }

    public void withdrawMember() {
        // 회원탈퇴상태로 변경
        status="N";
        //SetStatus(GetStatus("N"));
    }
}
