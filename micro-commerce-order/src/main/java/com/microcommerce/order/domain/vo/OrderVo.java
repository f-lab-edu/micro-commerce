package com.microcommerce.order.domain.vo;

import com.microcommerce.order.domain.dto.req.OrderReqDto;
import com.microcommerce.order.domain.enums.PaymentMethod;

import java.util.List;

public record OrderVo(
        Long userId,
        String address,
        String zipcode,
        PaymentMethod paymentMethod,
        List<OrderDetailVo> products
) {

    public static OrderVo getInstance(OrderReqDto dto) {
        return new OrderVo(
                dto.userId(), dto.address(), dto.zipcode(), dto.paymentMethod(),
                dto.products().stream().map(OrderDetailVo::getInstance).toList()
        );
    }

}
