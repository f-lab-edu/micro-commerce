package com.microcommerce.product.application;

import com.microcommerce.product.domain.dto.res.CreateProductResDto;
import com.microcommerce.product.domain.vo.CreateProductVo;

public interface ProductService {

    CreateProductResDto createProduct(CreateProductVo data);

}
