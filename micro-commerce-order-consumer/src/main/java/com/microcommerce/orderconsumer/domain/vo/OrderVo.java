package com.microcommerce.orderconsumer.domain.vo;

import com.microcommerce.orderconsumer.domain.enums.PaymentMethod;

import java.util.List;

public record OrderVo(String txId,
                      Long userId,
                      String address,
                      String zipcode,
                      PaymentMethod paymentMethod,
                      List<OrderDetailVo> products) {
}
