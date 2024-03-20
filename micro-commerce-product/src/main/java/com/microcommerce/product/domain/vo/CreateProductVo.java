package com.microcommerce.product.domain.vo;

import java.util.List;

public record CreateProductVo(
        Long sellerId,
        String name,
        Integer price,
        String category,
        String description,
        Integer stock,
        List<String> imageUrls) {
}
