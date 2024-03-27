package com.microcommerce.orderconsumer.mapper;

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
    @Mapping(source = "vo.productId", target = "productId")
    @Mapping(source = "vo.representativeImageUrl", target = "productRepresentativeImage")
    @Mapping(source = "vo.productName", target = "productName")
    @Mapping(source = "orderPrice", target = "price")
    @Mapping(source = "vo.quantity", target = "quantity")
    @Mapping(source = "vo.sellerId", target = "sellerId")
    @Mapping(source = "vo.sellerName", target = "sellerName")
    OrderDetail orderDetailVoToEntity(OrderDetailVo vo, Long orderId, Integer orderPrice);

    OrderVo OrderRecordToVo(OrderRecord vo);
}
