package com.microcommerce.product.domain.dto.req;

public record CreateProductReqDto(
        Long sellerId,
        String name,
        Long price,
        String category,
        String description,
        Long stock
) {
}
