package com.microcommerce.order.domain.vo;

import java.util.List;

public record OrderVo(
        Long userId,
        String address,
        String zipcode,
        List<OrderDetailVo> products
) {
}
