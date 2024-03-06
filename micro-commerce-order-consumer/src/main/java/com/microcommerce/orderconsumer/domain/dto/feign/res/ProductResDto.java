package com.microcommerce.orderconsumer.domain.dto.feign.res;

public record ProductResDto(Long id,
                            String name,
                            Long price,
                            String imageUrl,
                            String sellerName) {
}
