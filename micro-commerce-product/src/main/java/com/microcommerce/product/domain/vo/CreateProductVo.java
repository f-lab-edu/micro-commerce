package com.microcommerce.product.domain.vo;

public record CreateProductVo(
        Long sellerId,
        String name,
        Integer price,
        String category,
        String description,
        Integer stock) {
}
