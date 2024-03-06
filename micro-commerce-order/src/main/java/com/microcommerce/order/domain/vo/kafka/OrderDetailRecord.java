package com.microcommerce.order.domain.vo.kafka;

import com.microcommerce.order.domain.vo.OrderDetailVo;

public record OrderDetailRecord(Long productId, Long quantity) {

    public static OrderDetailRecord getInstance(OrderDetailVo vo) {
        return new OrderDetailRecord(vo.productId(), vo.quantity());
    }

}
