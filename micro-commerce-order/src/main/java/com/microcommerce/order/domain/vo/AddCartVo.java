package com.microcommerce.order.domain.vo;

public record AddCartVo(Long userId,
                        Long productId,
                        int quantity) {
}
