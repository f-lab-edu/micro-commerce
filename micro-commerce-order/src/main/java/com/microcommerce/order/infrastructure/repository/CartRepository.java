package com.microcommerce.order.infrastructure.repository;

import com.microcommerce.order.domain.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByUserId(Long userId);

}
