package com.microcommerce.memberconsumer.application;

import com.microcommerce.memberconsumer.domain.constant.PointTxType;
import com.microcommerce.memberconsumer.domain.entity.PointHistory;
import com.microcommerce.memberconsumer.infrastructure.repository.MemberWalletRepository;
import com.microcommerce.memberconsumer.infrastructure.repository.PointHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberWalletRepository memberWalletRepository;

    private final PointHistoryRepository pointHistoryRepository;

    @Transactional
    public void payment(final Long userId, final Integer price) {
        memberWalletRepository.findByUserId(userId)
                .map(w -> {
                    w.minus(price);

                    // FIXME: TransactionalEventListener로 빼기
                    final PointHistory history = PointHistory.getInstance(userId, price, PointTxType.PAYMENT);
                    pointHistoryRepository.save(history);
                    return w;
                });
    }

}
