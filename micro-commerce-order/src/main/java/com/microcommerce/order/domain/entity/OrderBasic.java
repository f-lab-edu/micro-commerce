package com.microcommerce.order.domain.entity;

import com.microcommerce.order.domain.enums.PaymentMethod;
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
public class OrderBasic extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long buyerId;

    @Column
    private String address;

    @Column
    private String zipcode;

    @Column
    private String invoiceNumber;

    @Column
    private PaymentMethod paymentMethod;

    @Column
    private LocalDateTime paymentAt;

}
