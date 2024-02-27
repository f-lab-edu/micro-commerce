package com.microcommerce.product.domain.dto.feign.res;


public record ProfileResDto(
        String email,
        String name,
        String phoneNumber,
        String type
) {
}
