package com.microcommerce.product.domain.dto.req;

public record CreateProductReqDto(
        Long sellerId,
        String name,
        Integer price,
        String category,
        String description,
        Integer stock
) {
}
