package com.microcommerce.product.infrastructure.repository;

import com.microcommerce.product.domain.dto.res.ProductResDto;

import java.util.List;

public interface ProductCustomRepository {

    List<ProductResDto> getProducts();

}
