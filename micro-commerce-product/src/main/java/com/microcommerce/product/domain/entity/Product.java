package com.microcommerce.product.domain.entity;

import com.microcommerce.product.domain.enums.ProductStatus;
import com.microcommerce.product.domain.vo.CreateProductVo;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends BaseEntity {

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
    private Integer price;

    // FIXME: 임시 String
    @Column
    private String category;

    @Lob
    @Column
    private String description;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column
    private ProductStatus status;

    @Setter
    @Column
    private Integer stock;

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

    public void decreaseStock(int quantity) {
        this.stock = this.stock - quantity;
    }

}
