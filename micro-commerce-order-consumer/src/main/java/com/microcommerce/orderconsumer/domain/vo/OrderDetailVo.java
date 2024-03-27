package com.microcommerce.orderconsumer.domain.vo;

public record OrderDetailVo(Long productId,
                            Integer productPrice,
                            String representativeImageUrl,
                            String productName,
                            Long sellerId,
                            String sellerName,
                            Integer quantity) {
}
