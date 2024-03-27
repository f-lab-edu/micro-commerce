package com.microcommerce.orderconsumer.domain.vo;

import java.util.List;

public record OrderVo(String txId,
                      Long userId,
                      String address,
                      String zipcode,
                      List<OrderDetailVo> products) {
}
