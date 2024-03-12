package com.microcommerce.product.presentation;

import com.microcommerce.product.application.StockService;
import com.microcommerce.product.domain.dto.req.DecreaseStockReqDto;
import com.microcommerce.product.domain.dto.req.SetStockReqDto;
import com.microcommerce.product.domain.dto.res.DecreaseStockResDto;
import com.microcommerce.product.domain.dto.res.SetStockResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class StockController {

    private final StockService stockService;

    // 주문 들아올 시 재고 차감
    @PatchMapping("/api/v1/products/{productId}/stock")
    public DecreaseStockResDto decreaseStock(@RequestBody DecreaseStockReqDto body, @PathVariable Long productId) {
        return stockService.decreaseStock(productId, body.quantity());
    }

    // 판매자가 자신의 상품에 대한 재고 수량 변경
    @PutMapping("/api/v1/products/{productId}/stock")
    public SetStockResDto setStock(@RequestBody SetStockReqDto body, @PathVariable Long productId) {
        return stockService.setStock(productId, body.quantity());
    }

}
