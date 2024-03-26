package com.microcommerce.order.domain.dto.res;

public record CartProductResDto(Long productId,
                                String productName,
                                Integer quantity,
                                Integer productPrice,
                                String representativeImageUrl) {
}
