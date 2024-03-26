package com.microcommerce.order.domain.vo.kafka;

import com.microcommerce.order.domain.enums.PaymentMethod;

import java.util.List;

public record OrderRecord(String txId,
                          Long userId,
                          String address,
                          String zipcode,
                          PaymentMethod paymentMethod,
                          List<OrderDetailRecord> products) {

}
