package com.microcommerce.product.infrastructure.repository;

import com.microcommerce.product.domain.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    List<ProductImage> findAllByProductIdOrderByDisplayOrder(Long productId);

}
