package com.microcommerce.payment.infrastructure.repository;

import com.microcommerce.payment.domain.entity.Wallet;

import java.util.Optional;

public interface WalletCustomRepository {

    Optional<Wallet> findByUserIdForUpdate(Long userId);

}
