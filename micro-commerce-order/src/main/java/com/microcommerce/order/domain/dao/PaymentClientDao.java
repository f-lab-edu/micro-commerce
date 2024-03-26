package com.microcommerce.order.domain.dao;

import com.microcommerce.order.exception.OrderException;
import com.microcommerce.order.exception.OrderExceptionCode;
import com.microcommerce.order.infrastructure.feign.PaymentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PaymentClientDao {

    private final PaymentClient paymentClient;

    public Integer getUserBalance(final Long userId) {
        final ResponseEntity<Integer> balanceRes = paymentClient.getUserBalance(userId);

        // 재고 차감 API 실패 시 처리
        if (!balanceRes.getStatusCode().is2xxSuccessful() || balanceRes.getBody() == null) {
            throw new OrderException(OrderExceptionCode.BAD_REQUEST);
        }
        return balanceRes.getBody();
    }

}
