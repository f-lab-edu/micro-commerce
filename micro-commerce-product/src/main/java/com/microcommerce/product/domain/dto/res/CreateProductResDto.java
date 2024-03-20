package com.microcommerce.product.domain.dto.res;

public record CreateProductResDto(String productName,
                                  Integer price,
                                  Integer stock) {
}
