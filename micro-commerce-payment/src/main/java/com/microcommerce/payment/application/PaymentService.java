package com.microcommerce.payment.application;

import com.microcommerce.payment.domain.constant.PointTxType;
import com.microcommerce.payment.domain.entity.PaymentHistory;
import com.microcommerce.payment.domain.entity.Wallet;
import com.microcommerce.payment.exception.PaymentException;
import com.microcommerce.payment.exception.PaymentExceptionCode;
import com.microcommerce.payment.infrastructure.repository.PointHistoryRepository;
import com.microcommerce.payment.infrastructure.repository.WalletRepository;
import com.microcommerce.payment.mapper.PaymentMapper;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final WalletRepository walletRepository;

    private final PointHistoryRepository pointHistoryRepository;

    private final PaymentMapper paymentMapper;
    @Transactional
    public void inout(final Long userId, final int point, final PointTxType type, final String txId) {
        walletRepository.findByUserIdForUpdate(userId).ifPresentOrElse(
                w -> {
                    // 중복 요청 exception
                    if (pointHistoryRepository.existsByTxId(txId)) {
                        throw new PaymentException(PaymentExceptionCode.DUPLICATE_REQUEST);
                    }

                    // 계산 후 잔액이 마이너스인 경우
                    if (w.getBalance() + point < 0) {
                        throw new PaymentException(PaymentExceptionCode.INSUFFICIENT_BALANCE);
                    }

                    w.setBalance(w.getBalance() + point);

                    final PaymentHistory history = paymentMapper.toPaymentHistory(userId, point, type, txId);
                    pointHistoryRepository.save(history);
                },
                () -> {
                    if (point < 0) {
                        throw new PaymentException(PaymentExceptionCode.WALLET_NOT_FOUND);
                    }
                    walletRepository.save(paymentMapper.toWallet(userId, point));
                }
        );
    }

    @Transactional(readOnly = true)
    public int getBalance(Long userId) {
        return walletRepository.findByUserId(userId)
                .map(Wallet::getBalance)
                .orElse(0);
    }

}
