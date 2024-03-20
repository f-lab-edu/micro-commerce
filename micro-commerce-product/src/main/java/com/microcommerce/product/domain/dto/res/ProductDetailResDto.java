package com.microcommerce.product.domain.dto.res;

import com.microcommerce.product.domain.enums.ProductStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ProductDetailResDto(Long id,
                                  String name,
                                  Integer price,
                                  List<String> imageUrl,
                                  String sellerName,
                                  String category,
                                  String description,
                                  ProductStatus status,
                                  LocalDateTime createdAt
) {
}
