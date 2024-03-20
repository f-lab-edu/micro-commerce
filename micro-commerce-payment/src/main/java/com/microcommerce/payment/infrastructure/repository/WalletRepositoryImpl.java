package com.microcommerce.payment.infrastructure.repository;

import com.microcommerce.payment.domain.entity.Wallet;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.microcommerce.payment.domain.entity.QWallet.wallet;

@RequiredArgsConstructor
public class WalletRepositoryImpl implements WalletCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Wallet> findByUserIdForUpdate(Long userId) {
        return Optional.of(
                jpaQueryFactory.selectFrom(wallet)
                        .where(wallet.userId.eq(userId))
                        .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                        .fetchFirst()
        );
    }
}
