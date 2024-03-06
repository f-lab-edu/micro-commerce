package com.microcommerce.product.domain.dto.res;

import com.microcommerce.product.domain.entity.Product;
import com.microcommerce.product.domain.entity.ProductImage;
import com.microcommerce.product.domain.enums.ProductStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ProductDetailResDto(
        Long id,
        String name,
        Long price,
        List<String> imageUrl,
        String sellerName,
        String category,
        String description,
        ProductStatus status,
        LocalDateTime createdAt
) {

    public static ProductDetailResDto getInstance(Product product, List<ProductImage> productImages) {
        return new ProductDetailResDto(
                product.getId(), product.getName(), product.getPrice(), productImages.stream().map(ProductImage::getUrl).toList(),
                product.getSellerName(), product.getCategory(), product.getDescription(), product.getStatus(), product.getCreatedAt()
        );
    }

}
