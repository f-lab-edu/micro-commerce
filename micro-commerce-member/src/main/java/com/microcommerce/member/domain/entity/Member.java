package com.microcommerce.member.domain.entity;

import com.microcommerce.member.domain.enums.MemberType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private MemberType type;

    // TODO: entity 객체 생성 방식에 대해서
    public static Member getInstance(final String email, final String hashedPw, final String name,
                                     final String phoneNumber, final MemberType type) {
        return Member.builder()
                .email(email)
                .password(hashedPw)
                .name(name)
                .phoneNumber(phoneNumber)
                .type(type)
                .build();
    }

}
