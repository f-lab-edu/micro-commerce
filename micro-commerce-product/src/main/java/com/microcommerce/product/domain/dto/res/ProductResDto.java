package com.microcommerce.product.domain.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ProductResDto {
    Long id;
    String name;
    Integer price;
    String representativeImageUrl;
    Long sellerId;
    String sellerName;
    Integer stock;
}
