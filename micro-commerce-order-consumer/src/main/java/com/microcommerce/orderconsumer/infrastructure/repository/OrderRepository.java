package com.microcommerce.orderconsumer.infrastructure.repository;

import com.microcommerce.orderconsumer.domain.entity.OrderBasic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface OrderRepository extends JpaRepository<OrderBasic, Long> {

    boolean existsByBuyerIdAndCreatedAtAfter(Long userId, LocalDateTime time);

}
