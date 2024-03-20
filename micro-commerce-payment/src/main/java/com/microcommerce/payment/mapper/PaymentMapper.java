package com.microcommerce.payment.mapper;

import com.microcommerce.payment.domain.constant.PointTxType;
import com.microcommerce.payment.domain.entity.PaymentHistory;
import com.microcommerce.payment.domain.entity.Wallet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    Wallet toWallet(Long userId, int balance);

    PaymentHistory toPaymentHistory(Long userId, int point, PointTxType type, String txId);

}
