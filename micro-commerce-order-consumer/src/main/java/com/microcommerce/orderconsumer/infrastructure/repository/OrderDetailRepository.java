package com.microcommerce.orderconsumer.infrastructure.repository;

import com.microcommerce.orderconsumer.domain.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
