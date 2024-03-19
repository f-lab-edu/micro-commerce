package com.microcommerce.product.domain.entity;

import com.microcommerce.product.domain.enums.ProductStatus;
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

}
