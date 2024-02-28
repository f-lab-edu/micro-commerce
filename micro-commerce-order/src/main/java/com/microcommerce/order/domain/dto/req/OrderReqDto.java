package com.microcommerce.order.domain.dto.req;

import com.microcommerce.order.domain.enums.PaymentMethod;

public record OrderReqDto(
        Long productId,
        Long userId,
        Integer quantity,
        String address,
        String zipcode,
        PaymentMethod paymentMethod
) {
}
