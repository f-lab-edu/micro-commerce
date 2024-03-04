package com.microcommerce.order.presentation;

import com.microcommerce.order.application.OrderService;
import com.microcommerce.order.domain.dto.req.OrderReqDto;
import com.microcommerce.order.domain.vo.OrderVo;
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

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api/v1/order")
    void order(@RequestBody OrderReqDto req) {
        orderService.order(OrderVo.getInstance(req));
    }

}
