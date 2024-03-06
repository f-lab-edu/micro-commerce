package com.microcommerce.orderconsumer.domain.vo;


import com.microcommerce.orderconsumer.domain.vo.kafka.OrderDetailRecord;

public record OrderDetailVo(Long productId, Long quantity) {

    public static OrderDetailVo getInstance(OrderDetailRecord vo) {
        return new OrderDetailVo(vo.productId(), vo.quantity());
    }

}
