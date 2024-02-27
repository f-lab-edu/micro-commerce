package com.microcommerce.product.domain.entity;

import com.microcommerce.product.domain.enums.ProductStatus;
import com.microcommerce.product.domain.vo.CreateProductVo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long sellerId;

    @Column
    private String sellerName;

    @Column
    private String name;

    @Column
    private Long price;

    // FIXME: 임시 String
    @Column
    private String category;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    @Column
    private ProductStatus status;

    @Column
    private Long stock;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    public static Product getInstance(CreateProductVo data, String sellerName) {
        return Product.builder()
                .sellerId(data.sellerId())
                .sellerName(sellerName)
                .name(data.name())
                .price(data.price())
                .category(data.category())
                .description(data.description())
                .status(ProductStatus.AVAILABLE)
                .stock(data.stock())
                .build();

    }

}
