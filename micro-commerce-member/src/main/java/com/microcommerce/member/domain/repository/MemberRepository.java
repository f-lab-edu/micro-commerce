package com.microcommerce.member.domain.repository;

import com.microcommerce.member.domain.entity.Member;
import com.microcommerce.member.domain.enums.MemberType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmailAndType(String email, MemberType type);

    Optional<Member> findByEmailAndType(String email, MemberType type);

}
