package com.microcommerce.order.mapper;

import com.microcommerce.order.domain.dto.feign.res.ProductResDto;
import com.microcommerce.order.domain.dto.req.OrderReqDto;
import com.microcommerce.order.domain.vo.OrderDetailVo;
import com.microcommerce.order.domain.vo.OrderVo;
import com.microcommerce.order.domain.vo.kafka.OrderDetailRecord;
import com.microcommerce.order.domain.vo.kafka.OrderRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "products", source = "products")
    OrderRecord toOrderRecord(OrderVo vo, String txId, List<OrderDetailRecord> products);

    @Mapping(target = "productPrice", source = "dto.price")
    @Mapping(target = "productName", source = "dto.name")
    OrderDetailRecord toOrderDetailRecord(OrderDetailVo vo, ProductResDto dto);

    OrderVo toOrderVo(OrderReqDto dto);

}
