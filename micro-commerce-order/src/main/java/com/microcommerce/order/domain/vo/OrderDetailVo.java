package com.microcommerce.order.domain.vo;

public record OrderDetailVo(
        Long productId,
        Integer quantity
) {
}
