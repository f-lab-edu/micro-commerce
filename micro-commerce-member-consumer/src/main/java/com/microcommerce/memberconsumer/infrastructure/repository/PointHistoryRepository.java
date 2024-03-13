package com.microcommerce.memberconsumer.infrastructure.repository;

import com.microcommerce.memberconsumer.domain.entity.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {
}
