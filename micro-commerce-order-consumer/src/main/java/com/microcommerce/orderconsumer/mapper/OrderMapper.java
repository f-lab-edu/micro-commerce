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

    OrderDetail orderDetailVoToEntity(OrderDetailVo orderDetailVo);

    OrderVo OrderRecordToVo(OrderRecord vo);
}
