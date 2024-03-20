package com.microcommerce.product.domain.dto.req;

import java.util.List;

public record CreateProductReqDto(
        Long sellerId,
        String name,
        Integer price,
        String category,
        String description,
        Integer stock,
        List<String> imageUrls
) {
}
