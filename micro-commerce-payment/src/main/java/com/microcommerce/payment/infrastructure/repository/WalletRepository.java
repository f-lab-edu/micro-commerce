package com.microcommerce.payment.infrastructure.repository;

import com.microcommerce.payment.domain.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long>, WalletCustomRepository {

    Optional<Wallet> findByUserId(Long userId);

}
