package com.microcommerce.payment.domain.dto.req;

import com.microcommerce.payment.domain.constant.PointTxType;

public record PaymentReqDto(Long userId,
                            int price,
                            PointTxType type,
                            String txId) {
}
