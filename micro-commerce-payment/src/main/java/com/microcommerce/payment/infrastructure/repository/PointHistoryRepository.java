package com.microcommerce.payment.infrastructure.repository;

import com.microcommerce.payment.domain.entity.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointHistoryRepository extends JpaRepository<PaymentHistory, Long> {

    boolean existsByTxId(String txId);

}
