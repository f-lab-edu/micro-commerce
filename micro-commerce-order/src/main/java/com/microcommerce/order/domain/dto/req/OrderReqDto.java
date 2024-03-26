package com.microcommerce.order.domain.dto.req;

import com.microcommerce.order.domain.enums.PaymentMethod;

import java.util.List;

public record OrderReqDto(
        Long userId,
        String address,
        String zipcode,
        PaymentMethod paymentMethod,
        List<OrderDetailReqDto> products,
        Long totalOrderPrice
) {
}
