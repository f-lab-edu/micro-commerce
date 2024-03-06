package com.microcommerce.orderconsumer.infrastructure.repository;

import com.microcommerce.orderconsumer.domain.entity.OrderBasic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderBasic, Long> {
}
