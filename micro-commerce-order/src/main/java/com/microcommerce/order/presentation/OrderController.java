package com.microcommerce.order.presentation;

import com.microcommerce.order.application.OrderService;
import com.microcommerce.order.domain.dto.ApiResult;
import com.microcommerce.order.domain.dto.req.OrderReqDto;
import com.microcommerce.order.domain.vo.OrderVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/api/v1/order")
    ResponseEntity<ApiResult<Boolean>> order(@RequestBody OrderReqDto req) {
        orderService.order(OrderVo.getInstance(req));
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResult.success(true));
    }

}
