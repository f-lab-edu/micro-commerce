package com.microcommerce.orderconsumer.domain.dto.feign.res;

public record ProductResDto(Long id,
                            String name,
                            long price,
                            String imageUrl,
                            String sellerName,
                            int stock) {
}
