package com.microcommerce.orderconsumer.domain.vo;

import com.microcommerce.orderconsumer.domain.enums.PaymentMethod;
import com.microcommerce.orderconsumer.domain.vo.kafka.OrderRecord;

import java.util.List;

public record OrderVo(Long userId,
                      String address,
                      String zipcode,
                      PaymentMethod paymentMethod,
                      List<OrderDetailVo> products) {

    public static OrderVo getInstance(OrderRecord vo) {
        return new OrderVo(
                vo.userId(), vo.address(), vo.zipcode(), vo.paymentMethod(),
                vo.products().stream().map(OrderDetailVo::getInstance).toList()
        );
    }

}
