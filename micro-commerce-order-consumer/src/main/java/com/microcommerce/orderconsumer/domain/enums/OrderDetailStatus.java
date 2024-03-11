package com.microcommerce.orderconsumer.domain.enums;

public enum OrderDetailStatus {

    PAYMENT_PENDING, // 결제 대기
    ORDER_COMPLETE, // 주문 완료(결제 완료)
    DELIVERY_IN_PROGRESS, // 배송중
    DELIVERY_COMPLETED, // 배송 완료
    RETURN_REQUESTED, // 반품 신청
    RETURN_COMPLETED, // 반품 완료(환불 완료)
    EXCHANGE_REQUESTED, // 교환 신청
    EXCHANGE_IN_PROGRESS, // 교환 진행중(배송 진행중)
    EXCHANGE_COMPLETED, // 교환 완료
    ORDER_CANCELED, // 주문 취소(사용자가 취소)
    ORDER_REJECTED // 주문 반려(재고 부족 또는 그 외 사정으로 인해 주문이 불가능할 때)

}
