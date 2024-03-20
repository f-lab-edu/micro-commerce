package com.microcommerce.payment.infrastructure.repository;

import com.microcommerce.payment.domain.entity.Wallet;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    Optional<Wallet> findByUserId(Long userId);

}
