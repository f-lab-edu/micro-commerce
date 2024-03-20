package com.microcommerce.orderconsumer.mapper;

import com.microcommerce.orderconsumer.domain.dto.feign.res.ProductResDto;
import com.microcommerce.orderconsumer.domain.entity.OrderBasic;
import com.microcommerce.orderconsumer.domain.entity.OrderDetail;
import com.microcommerce.orderconsumer.domain.vo.OrderDetailVo;
import com.microcommerce.orderconsumer.domain.vo.OrderVo;
import com.microcommerce.orderconsumer.domain.vo.kafka.OrderRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "userId", target = "buyerId")
    OrderBasic orderVoToEntity(OrderVo orderVo);

    @Mapping(source = "orderId", target = "orderId")
    @Mapping(source = "orderDetailVo.productId", target = "productId")
    @Mapping(source = "productResDto.representativeImageUrl", target = "productRepresentativeImage")
    @Mapping(source = "productResDto.name", target = "productName")
    @Mapping(source = "productResDto.price", target = "price")
    @Mapping(source = "orderDetailVo.quantity", target = "quantity")
    @Mapping(source = "productResDto.sellerId", target = "sellerId")
    @Mapping(source = "productResDto.sellerName", target = "sellerName")
    OrderDetail orderDetailVoToEntity(OrderDetailVo orderDetailVo, ProductResDto productResDto, Long orderId);

    OrderVo OrderRecordToVo(OrderRecord vo);
}
