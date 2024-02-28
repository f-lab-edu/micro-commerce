package com.microcommerce.member.domain.repository;

import com.microcommerce.member.domain.entity.Member;
import com.microcommerce.member.domain.enums.MemberType;
import com.microcommerce.member.infrastructure.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@TestPropertySource(locations = "classpath:application-test.yml")
@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("회원이 DB에 잘 저장되는지 테스트")
    @Test
    void createMember() {
        final Member member1 = Member.getInstance("test1@test.com", "hashed password example1", "테스트 유저1", "01011111111", MemberType.NORMAL);
        final Member member2 = Member.getInstance("test2@test.com", "hashed password example2", "테스트 유저2", "01022222222", MemberType.SELLER);
        final Member newMember1 = memberRepository.save(member1);
        final Member newMember2 = memberRepository.save(member2);

        assertThat(newMember1.getEmail()).isEqualTo("test1@test.com");
        assertThat(newMember1.getPassword()).isEqualTo("hashed password example1");
        assertThat(newMember1.getName()).isEqualTo("테스트 유저1");
        assertThat(newMember1.getPhoneNumber()).isEqualTo("01011111111");
        assertThat(newMember1.getType()).isEqualTo(MemberType.NORMAL);

        assertThat(newMember2.getEmail()).isEqualTo("test2@test.com");
        assertThat(newMember2.getPassword()).isEqualTo("hashed password example2");
        assertThat(newMember2.getName()).isEqualTo("테스트 유저2");
        assertThat(newMember2.getPhoneNumber()).isEqualTo("01022222222");
        assertThat(newMember2.getType()).isEqualTo(MemberType.SELLER);
    }

    @DisplayName("회원 전체 조회 테스트")
    @Test
    void getMemberList() {
        final Member member1 = Member.getInstance("test1@test.com", "hashed password example1", "테스트 유저1", "01011111111", MemberType.NORMAL);
        final Member member2 = Member.getInstance("test2@test.com", "hashed password example2", "테스트 유저2", "01022222222", MemberType.SELLER);
        memberRepository.save(member1);
        memberRepository.save(member2);


        List<Member> members = memberRepository.findAll();
        assertThat(members.size()).isEqualTo(2);
    }

    @DisplayName("회원 단건 조회 테스트")
    @Test
    void getMember() {
        final Member member1 = Member.getInstance("test1@test.com", "hashed password example1", "테스트 유저1", "01011111111", MemberType.NORMAL);
        final Member member2 = Member.getInstance("test2@test.com", "hashed password example2", "테스트 유저2", "01022222222", MemberType.SELLER);
        final Member newMember1 = memberRepository.save(member1);
        final Member newMember2 = memberRepository.save(member2);


        Optional<Member> oMember1 = memberRepository.findById(newMember1.getId());
        Optional<Member> oMember2 = memberRepository.findById(newMember2.getId());
        assertThat(oMember1.isPresent()).isTrue();
        assertThat(oMember2.isPresent()).isTrue();

        Member selectedMember1 = oMember1.get();
        Member selectedMember2 = oMember2.get();

        assertThat(selectedMember1.getEmail()).isEqualTo("test1@test.com");
        assertThat(selectedMember1.getPassword()).isEqualTo("hashed password example1");
        assertThat(selectedMember1.getName()).isEqualTo("테스트 유저1");
        assertThat(selectedMember1.getPhoneNumber()).isEqualTo("01011111111");
        assertThat(selectedMember1.getType()).isEqualTo(MemberType.NORMAL);

        assertThat(selectedMember2.getEmail()).isEqualTo("test2@test.com");
        assertThat(selectedMember2.getPassword()).isEqualTo("hashed password example2");
        assertThat(selectedMember2.getName()).isEqualTo("테스트 유저2");
        assertThat(selectedMember2.getPhoneNumber()).isEqualTo("01022222222");
        assertThat(selectedMember2.getType()).isEqualTo(MemberType.SELLER);
    }

}