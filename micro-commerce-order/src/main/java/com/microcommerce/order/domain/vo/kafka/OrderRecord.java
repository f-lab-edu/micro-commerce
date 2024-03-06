package com.microcommerce.order.domain.vo.kafka;

import com.microcommerce.order.domain.enums.PaymentMethod;
import com.microcommerce.order.domain.vo.OrderVo;

import java.util.List;

public record OrderRecord(Long userId,
                          String address,
                          String zipcode,
                          PaymentMethod paymentMethod,
                          List<OrderDetailRecord> products) {

    // TODO: [질문] 하나의 레이어에서만 사용하려하는데 같은 구조의 클래스가 반복됩니다.
    public static OrderRecord getInstance(OrderVo vo) {
        return new OrderRecord(
                vo.userId(), vo.address(), vo.zipcode(), vo.paymentMethod(),
                vo.products().stream().map(OrderDetailRecord::getInstance).toList()
        );
    }

}
