package com.microcommerce.orderconsumer.domain.dto.feign.req;

public record DecreaseStockReqDto(String txId, Integer quantity) {
}
