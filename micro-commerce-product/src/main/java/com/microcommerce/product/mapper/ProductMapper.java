package com.microcommerce.product.mapper;

import com.microcommerce.product.domain.dto.req.CreateProductReqDto;
import com.microcommerce.product.domain.dto.res.CreateProductResDto;
import com.microcommerce.product.domain.dto.res.ProductDetailResDto;
import com.microcommerce.product.domain.dto.res.ProductResDto;
import com.microcommerce.product.domain.entity.Product;
import com.microcommerce.product.domain.vo.CreateProductVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "name", target = "productName")
    CreateProductResDto toCreateProductResDto(Product product);

    CreateProductVo toCreateProductVo(CreateProductReqDto req);

    @Mapping(source = "images", target = "imageUrl")
    ProductDetailResDto toProductDetailResDto(Product product, List<String> images);

    @Mapping(source = "imageUrl", target = "representativeImageUrl")
    ProductResDto toProductResDto(Product product, String imageUrl);

    Product toProduct(CreateProductVo data, String sellerName);

}
