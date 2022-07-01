package scramble.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
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

    private String memberId;

    private String password;

    private String memberName;

    private String phoneNumber;

    private String status;

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
}
