package com.microcommerce.orderconsumer.domain.vo.kafka;

public record PointChangeRecord(Long userId,
                                Long price,
                                String description) {
}
