package com.microcommerce.orderconsumer.domain.vo.kafka;


import com.microcommerce.orderconsumer.domain.enums.PaymentMethod;

public record OrderRecord(Long productId,
                          Long userId,
                          Integer quantity,
                          String address,
                          String zipcode,
                          PaymentMethod paymentMethod) {
}
