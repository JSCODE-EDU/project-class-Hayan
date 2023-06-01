package hayan.member.service;

import hayan.global.exception.CustomException;
import hayan.global.exception.ErrorInformation;
import hayan.member.domain.Member;
import hayan.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(String email, String password) {
        validateDuplication(email);

        String encryptedPassword = passwordEncoder.encode(password);

        Member member = new Member(email, encryptedPassword);

        memberRepository.save(member);
    }

    @Transactional
    public void logIn(String email, String password) {

    }

    private void validateDuplication(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new CustomException(ErrorInformation.DUPLICATE_MEMBER);
        }
    }
}
