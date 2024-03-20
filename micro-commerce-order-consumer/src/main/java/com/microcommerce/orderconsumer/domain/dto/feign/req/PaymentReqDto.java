package com.microcommerce.orderconsumer.domain.dto.feign.req;

public record PaymentReqDto(Long userId,
                            Long price,
                            String txId) {
}
