package com.microcommerce.product.domain.dao;

import com.microcommerce.product.domain.dto.feign.res.ProfileResDto;
import com.microcommerce.product.exception.ProductException;
import com.microcommerce.product.exception.ProductExceptionCode;
import com.microcommerce.product.infrastructure.feign.MemberClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberClientDao {

    private final MemberClient memberClient;

    public ProfileResDto getProfile(final Long userId) {
        final ResponseEntity<ProfileResDto> memberRes = memberClient.getProfile(userId);
        if (!memberRes.getStatusCode().is2xxSuccessful() || memberRes.getBody() == null) {
            throw new ProductException(ProductExceptionCode.USER_NOT_FOUND);
        }
        return memberRes.getBody();
    }

}
