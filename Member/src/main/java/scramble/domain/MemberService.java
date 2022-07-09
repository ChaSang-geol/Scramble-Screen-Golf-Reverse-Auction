package scramble.domain;

import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

//import lombok.var;

import org.springframework.security.crypto.password.PasswordEncoder;

public class MemberService implements MemberFindService{

  private final PasswordEncoder passwordEncoder;
  private final MemberRepository memberRepository;

  public MemberService(PasswordEncoder passwordEncoder, MemberRepository memberRepository) {
      this.passwordEncoder = passwordEncoder;
      this.memberRepository = memberRepository;
  }

  @Transactional
  public Member signUp(Member request) {
      final Member member = new Member();
      //final var encodedPassword = Password of(request.getPassword(), passwordEncoder);
      member.setPassword(passwordEncoder.encode(request.getPassword()));
      member.setRole(Role.MEMBER);
      member.setStatus("Y");
      member.setMemberId(request.getMemberId());
      member.setMemberName(request.getMemberName());
      member.setPhoneNumber(request.getPhoneNumber());
      return memberRepository.save(member);
  }

  @Transactional(readOnly = true)
  public Optional<Member> login(String memberId, String rawPassword) {
      return memberRepository.findByMemberId(memberId)
              .filter(member -> member.matchesPassword(rawPassword, passwordEncoder));
  }

  @Override
  public Optional<Member> findById(long id) {
    return memberRepository.findById(id);
  }

  @Override
  public Optional<Member> findByMemberId(String memberId) {
    return memberRepository.findByMemberId(memberId);
  }

  @Transactional
  public Member updateMember(long id, Member request) {
      final Member user = memberRepository.findById(id).orElseThrow(NoSuchElementException::new);

        if (Optional.ofNullable(request.getMemberId()).isPresent()){
                user.setMemberId(request.getMemberId());
        }
        if (Optional.ofNullable(request.getMemberName()).isPresent()){
                user.setMemberName(request.getMemberName());
        }
        if (Optional.ofNullable(request.getPassword()).isPresent()){
                user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        if (Optional.ofNullable(request.getPhoneNumber()).isPresent()){
                user.setPhoneNumber(request.getPhoneNumber());
        }


      return memberRepository.save(user);
  }

}
