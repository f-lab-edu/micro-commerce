package com.microcommerce.orderconsumer.domain.vo.kafka;

import com.microcommerce.orderconsumer.domain.enums.PaymentMethod;

import java.util.List;

public record OrderRecord(Long userId,
                          String address,
                          String zipcode,
                          PaymentMethod paymentMethod,
                          List<OrderDetailRecord> products) {
}
