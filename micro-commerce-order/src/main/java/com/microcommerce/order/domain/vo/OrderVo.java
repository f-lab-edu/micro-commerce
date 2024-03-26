package com.microcommerce.order.domain.vo;

import com.microcommerce.order.domain.enums.PaymentMethod;

import java.util.List;

public record OrderVo(
        Long userId,
        String address,
        String zipcode,
        PaymentMethod paymentMethod,
        List<OrderDetailVo> products,
        Long totalOrderPrice
) {
}
