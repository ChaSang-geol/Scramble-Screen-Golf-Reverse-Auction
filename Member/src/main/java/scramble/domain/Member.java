package scramble.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.persistence.metamodel.SetAttribute;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Builder;
import lombok.Data;
import scramble.MemberApplication;


@Entity
@Table(name = "Member_table")
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique=true, nullable = false)
    private String memberId; // login ID
    private String password; //
    private String memberName; // 이름
    private String phoneNumber; // 휴대전화번호
    private String status="Y"; // 상태 : Y 가입, N 탈퇴
    @Embedded
    //@Builder.Default
    Role role = Role.MEMBER; // 회원의 구분 & 권한
/*
    public String getRole() {
        return role.toString();
    }
*/
    @PostPersist
    public void onPostPersist() {
        /*
         *  회원가입후 처리
         */
        MemberJoined memberJoined = new MemberJoined(this);
        BeanUtils.copyProperties(this, memberJoined);

        // memberJoined.setMemberId(this.getMemberId());
        // memberJoined.setMemberName(this.getMemberName());
        // memberJoined.setPhoneNumber(this.getPhoneNumber());
        // memberJoined.setStatus(this.getStatus());
        // memberJoined.setRole(this.getRole().toString());

        memberJoined.publishAfterCommit();


        /*
         * 회원 로그인후 처리
         */
        /*
        MemberLoggedIn memberLoggedIn = new MemberLoggedIn(this);
        memberLoggedIn.setMemberId(this.getMemberId());
        memberLoggedIn.setMemberName(this.getMemberName());
        memberLoggedIn.setPhoneNumber(this.getPhoneNumber());
        memberLoggedIn.setStatus(this.getStatus());

        memberLoggedIn.publishAfterCommit();
         */
    }
    @PostUpdate
    public void onPostUpdate() {
         /*
         * 회원정보 수정후 처리
         */
        MemberUpdated memberUpdated = new MemberUpdated(this);
        BeanUtils.copyProperties(this, memberUpdated);

        // memberUpdated.setMemberId(this.getMemberId());
        // memberUpdated.setMemberName(this.getMemberName());
        // memberUpdated.setPhoneNumber(this.getPhoneNumber());
        // memberUpdated.setStatus(this.getStatus());
        // memberUpdated.setRole(this.getRole().toString());

        memberUpdated.publishAfterCommit();

        /*
         * 회원탈퇴후 처리
         */
        if ("N".equals(this.getStatus())) {
            MemberWithdrawed memberWithdrawed = new MemberWithdrawed(this);
            BeanUtils.copyProperties(this, memberWithdrawed);

            // memberWithdrawed.setMemberId(this.getMemberId());
            // memberWithdrawed.setMemberName(this.getMemberName());
            // memberWithdrawed.setPhoneNumber(this.getPhoneNumber());
            // memberWithdrawed.setStatus(this.getStatus());

            memberWithdrawed.publishAfterCommit();
        }
    }

    // private final PasswordEncoder passwordEncoder;

    // @PrePersist
    // public void onPrePersist() {
    //     //비밀번호 암호화 저장
    //     this.setPassword(passwordEncoder.encode(getPassword()));

    // }

    // @PreUpdate
    // public void onPreUpdate() {
    //     //비밀번호 암호화 저장
    //     if (getPassword().isEmpty()) {
    //         this.setPassword(this.getPassword());
    //     } else {
    //         this.setPassword(passwordEncoder.encode(getPassword()));
    //     }

    // }
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

    boolean matchesPassword(String rawPassword, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(passwordEncoder.encode(rawPassword), password);
    }
}
