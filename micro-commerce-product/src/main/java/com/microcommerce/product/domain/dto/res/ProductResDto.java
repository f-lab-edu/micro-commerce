package com.microcommerce.product.domain.dto.res;

import lombok.Getter;

@Getter
public class ProductResDto {
    Long id;
    String name;
    Long price;
    String imageUrl;
    String sellerName;
}
