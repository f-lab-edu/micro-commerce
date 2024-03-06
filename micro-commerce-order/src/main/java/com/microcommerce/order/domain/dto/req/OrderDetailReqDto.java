package com.microcommerce.order.domain.dto.req;

public record OrderDetailReqDto(
        Long productId,
        Long quantity
) {
}
