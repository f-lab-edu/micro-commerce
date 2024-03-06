package com.microcommerce.order.domain.vo;

import com.microcommerce.order.domain.dto.req.OrderDetailReqDto;
import com.microcommerce.order.domain.dto.req.OrderReqDto;
import com.microcommerce.order.domain.enums.PaymentMethod;
import lombok.Builder;

public record OrderDetailVo(
        Long productId,
        Long quantity
) {

    public static OrderDetailVo getInstance(OrderDetailReqDto dto) {
        return new OrderDetailVo(dto.productId(), dto.quantity());
    }

}
