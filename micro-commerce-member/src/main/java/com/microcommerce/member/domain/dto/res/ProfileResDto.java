package com.microcommerce.member.domain.dto.res;

import com.microcommerce.member.domain.entity.Member;
import com.microcommerce.member.domain.enums.MemberType;
import lombok.Builder;

@Builder
public record ProfileResDto(String email, String name, String phoneNumber, MemberType type) {

    public static ProfileResDto getInstance(String email, String name, String phoneNumber, MemberType type) {
        return ProfileResDto.builder()
                .email(email)
                .name(name)
                .phoneNumber(phoneNumber)
                .type(type)
                .build();
    }

    public static ProfileResDto getInstance(Member member) {
        return ProfileResDto.builder()
                .email(member.getEmail())
                .name(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .type(member.getType())
                .build();
    }

}
