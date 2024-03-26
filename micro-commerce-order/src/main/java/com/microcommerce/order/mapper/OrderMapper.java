package com.microcommerce.order.mapper;

import com.microcommerce.order.domain.dto.req.OrderReqDto;
import com.microcommerce.order.domain.vo.OrderVo;
import com.microcommerce.order.domain.vo.kafka.OrderRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderRecord toOrderRecord(OrderVo vo, String txId);

    OrderVo toORderVo(OrderReqDto dto);

}
