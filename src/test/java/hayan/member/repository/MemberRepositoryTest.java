package hayan.member.repository;

import hayan.member.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void email_중복_테스트() {
        //given
        String email = "dfs@naver.com";
        memberRepository.save(new Member(email, "dfs"));

        //when
        boolean exist = memberRepository.existsByEmail(email);

        //then
        Assertions.assertThat(exist).isTrue();
    }

}