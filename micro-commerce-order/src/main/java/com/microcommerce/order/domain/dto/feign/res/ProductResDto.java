package com.microcommerce.order.domain.dto.feign.res;

public record ProductResDto(Long id,
                            String name,
                            int price,
                            String representativeImageUrl,
                            Long sellerId,
                            String sellerName,
                            int stock) {
}

