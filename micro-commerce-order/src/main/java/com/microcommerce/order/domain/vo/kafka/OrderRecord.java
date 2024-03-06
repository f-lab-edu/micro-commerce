package com.microcommerce.order.domain.vo.kafka;

import com.microcommerce.order.domain.enums.PaymentMethod;
import com.microcommerce.order.domain.vo.OrderVo;

public record OrderRecord(Long productId,
                          Long userId,
                          Integer quantity,
                          String address,
                          String zipcode,
                          PaymentMethod paymentMethod) {

    public static OrderRecord getInstance(OrderVo vo) {
        return new OrderRecord(vo.productId(), vo.userId(), vo.quantity(), vo.address(), vo.zipcode(), vo.paymentMethod());
    }

}
