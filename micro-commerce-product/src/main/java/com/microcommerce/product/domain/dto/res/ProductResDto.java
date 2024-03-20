package com.microcommerce.product.domain.dto.res;

import lombok.Getter;

@Getter
public class ProductResDto {
    Long id;
    String name;
    Integer price;
    public String representativeImageUrl;
    Long sellerId;
    String sellerName;
}
