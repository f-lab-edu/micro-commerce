package com.microcommerce.product.domain.dto.res;

import lombok.Builder;

@Builder
public record CreateProductResDto(String productName, Long price, Long stock) {

    public static CreateProductResDto getInstance(String productName, Long price, Long stock) {
        return CreateProductResDto.builder()
                .productName(productName)
                .price(price)
                .stock(stock)
                .build();
    }

}
