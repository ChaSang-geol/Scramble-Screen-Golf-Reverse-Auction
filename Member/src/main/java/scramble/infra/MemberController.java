package scramble.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import scramble.domain.*;

@RestController
@RequestMapping(value = "/members")
@Transactional
public class MemberController {

    @Autowired
    MemberRepository memberRepository;
    MemberService memberService;
    // keep

    // 회원로그인
    @PostMapping("/members/login")
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
    public void updateMember(){

    }

    // 회원탈퇴
    public String withdrawMember(){

        return "회원 탈퇴 처리가 완료되었습니다.";
    }

}
