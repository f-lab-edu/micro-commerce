package com.microcommerce.product.domain.dto.res;

import lombok.Getter;

@Getter
public class ProductResDto {
    Long id;
    String name;
    long price;
    String representativeImageUrl;
    Long sellerId;
    String sellerName;
}
