package com.microcommerce.orderconsumer.domain.dto.feign.res;

public record DecreaseStockResDto(boolean isSuccess,
                                  String message) {
}
