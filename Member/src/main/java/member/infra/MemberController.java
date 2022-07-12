package member.infra;

import java.util.Optional;
import javax.transaction.Transactional;
import javax.validation.Valid;

import member.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/members")
@Transactional
public class MemberController {

    @Autowired
    MemberRepository memberRepository;
    MemberService memberService;
    // keep

    // 회원로그인
    @PostMapping("/login")
    public MemberLoggedIn login(@Valid @RequestBody String memberId, String password) {
        // return of(memberService.login(memberId, password)
        //         .map(member -> fromUserAndToken(member, jwtSerializer.jwtFromUser(member))));
        MemberLoggedIn theloginmember = new MemberLoggedIn();
        Optional<Member> member = memberService.login(memberId, password);

        theloginmember.setMemberId(member.get().getMemberId());
        theloginmember.setMemberName(member.get().getMemberName());
        theloginmember.setPhoneNumber(member.get().getPhoneNumber());
        theloginmember.setStatus(member.get().getStatus());

        return theloginmember;
    }





    // 회원등록
    public void joinMember(){

    }

    // 회원정보수정
    @PutMapping("/members/{id}")
    public Member updateMember(@Valid @RequestBody RequestUpdateMember member, Long id ){
        Member themMember = new Member() ;
        try {
            themMember= memberService.updateMember(id, member);
        } catch (Exception e) {
            //TODO: handle exception

        }
        return themMember;

    }

    // 회원탈퇴
    public String withdrawMember(){

        return "회원 탈퇴 처리가 완료되었습니다.";
    }

}
