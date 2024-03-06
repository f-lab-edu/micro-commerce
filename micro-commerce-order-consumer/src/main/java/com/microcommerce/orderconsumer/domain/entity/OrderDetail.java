package com.microcommerce.orderconsumer.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long orderId;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private String productImage;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private Long sellerId;

    @Column(nullable = false)
    private String sellerName;

}
