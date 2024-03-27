package com.microcommerce.orderconsumer.domain.vo.kafka;

import java.util.List;

public record OrderRecord(String txId,
                          Long userId,
                          String address,
                          String zipcode,
                          List<OrderDetailRecord> products) {
}
