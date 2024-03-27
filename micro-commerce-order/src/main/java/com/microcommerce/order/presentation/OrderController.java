package com.microcommerce.order.presentation;

import com.microcommerce.order.application.OrderService;
import com.microcommerce.order.domain.dto.req.OrderReqDto;
import com.microcommerce.order.exception.OrderException;
import com.microcommerce.order.exception.OrderExceptionCode;
import com.microcommerce.order.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    private final OrderMapper orderMapper;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api/v1/orders")
    public void order(@RequestBody OrderReqDto req) {
        if (req.products() == null || req.products().isEmpty()) {
            throw new OrderException(OrderExceptionCode.BAD_REQUEST);
        }
        orderService.order(orderMapper.toOrderVo(req));
    }

}
