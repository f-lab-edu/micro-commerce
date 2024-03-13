package com.microcommerce.memberconsumer.infrastructure.repository;

import com.microcommerce.memberconsumer.domain.entity.MemberPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberWalletRepository extends JpaRepository<MemberPoint, Long> {

    Optional<MemberPoint> findByUserId(Long userId);

}
