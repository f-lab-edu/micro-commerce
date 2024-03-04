package com.microcommerce.product.infrastructure.feign;

import com.microcommerce.product.domain.dto.feign.res.ProfileResDto;
import com.microcommerce.product.exception.ProductException;
import com.microcommerce.product.exception.ProductExceptionCode;
import org.springframework.stereotype.Component;

@Component
public class MemberClientFallback implements MemberClient {

    @Override
    public ProfileResDto getProfile(Long userId) {
        throw new ProductException(ProductExceptionCode.INTERNAL_REQUEST_ERROR);
    }

}
