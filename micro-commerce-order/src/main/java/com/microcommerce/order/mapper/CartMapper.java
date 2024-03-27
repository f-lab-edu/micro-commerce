package com.microcommerce.order.mapper;

import com.microcommerce.order.domain.dto.feign.res.ProductResDto;
import com.microcommerce.order.domain.dto.req.AddCartReqDto;
import com.microcommerce.order.domain.dto.res.CartProductResDto;
import com.microcommerce.order.domain.entity.Cart;
import com.microcommerce.order.domain.vo.AddCartVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {

    AddCartVo toAddCartVo(AddCartReqDto dto);

    Cart toCart(AddCartVo vo);

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "productPrice", source = "product.price")
    @Mapping(target = "representativeImageUrl", source = "product.representativeImageUrl")
    CartProductResDto toCartProductResDto(ProductResDto product, Integer quantity);

}
