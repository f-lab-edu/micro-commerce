package com.microcommerce.product.domain.vo;

import com.microcommerce.product.domain.dto.req.CreateProductReqDto;
import lombok.Builder;

@Builder
public record CreateProductVo(
        Long sellerId,
        String name,
        Long price,
        String category,
        String description,
        Long stock) {

    public static CreateProductVo getInstance(CreateProductReqDto req) {
        return CreateProductVo.builder()
                .sellerId(req.sellerId())
                .name(req.name())
                .price(req.price())
                .category(req.category())
                .description(req.description())
                .stock(req.stock())
                .build();

    }

}
