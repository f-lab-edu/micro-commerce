package com.microcommerce.orderconsumer.domain.dto.feign.res;

public record ProductResDto(Long id,
                            String name,
                            long price,
                            String representativeImageUrl,
                            Long sellerId,
                            String sellerName,
                            int stock) {
}
