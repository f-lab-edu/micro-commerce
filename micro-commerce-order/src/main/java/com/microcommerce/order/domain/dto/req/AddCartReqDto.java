package com.microcommerce.order.domain.dto.req;

public record AddCartReqDto(Long userId,
                            Long productId,
                            int quantity) {
}
