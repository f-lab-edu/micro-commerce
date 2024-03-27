package com.microcommerce.order.domain.dto.req;

import java.util.List;

public record OrderReqDto(
        Long userId,
        String address,
        String zipcode,
        List<OrderDetailReqDto> products
) {
}
