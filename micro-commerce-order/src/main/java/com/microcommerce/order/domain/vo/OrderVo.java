package com.microcommerce.order.domain.vo;

import com.microcommerce.order.domain.dto.req.OrderReqDto;
import com.microcommerce.order.domain.enums.PaymentMethod;
import lombok.Builder;

@Builder
public record OrderVo(
        Long productId,
        Long userId,
        Integer quantity,
        String address,
        String zipcode,
        PaymentMethod paymentMethod
) {

    public static OrderVo getInstance(OrderReqDto data) {
        return OrderVo.builder()
                .productId(data.productId())
                .userId(data.userId())
                .quantity(data.quantity())
                .address(data.address())
                .zipcode(data.zipcode())
                .paymentMethod(data.paymentMethod())
                .build();
    }

}
