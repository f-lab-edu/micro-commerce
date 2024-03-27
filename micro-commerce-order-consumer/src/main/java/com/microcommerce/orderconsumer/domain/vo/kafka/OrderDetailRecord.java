package com.microcommerce.orderconsumer.domain.vo.kafka;

public record OrderDetailRecord(Long productId,
                                Integer productPrice,
                                String representativeImageUrl,
                                String productName,
                                Long sellerId,
                                String sellerName,
                                Integer quantity) {
}
