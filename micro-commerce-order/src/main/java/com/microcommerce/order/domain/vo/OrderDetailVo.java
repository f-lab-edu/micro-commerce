package com.microcommerce.order.domain.vo;

import com.microcommerce.order.domain.dto.req.OrderDetailReqDto;

public record OrderDetailVo(
        Long productId,
        Long quantity
) {

    public static OrderDetailVo getInstance(OrderDetailReqDto dto) {
        return new OrderDetailVo(dto.productId(), dto.quantity());
    }

}
