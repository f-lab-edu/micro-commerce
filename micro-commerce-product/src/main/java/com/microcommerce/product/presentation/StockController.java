package com.microcommerce.product.presentation;

import com.microcommerce.product.application.StockService;
import com.microcommerce.product.domain.dto.req.DecreaseStockReqDto;
import com.microcommerce.product.domain.dto.res.DecreaseStockResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class StockController {

    private final StockService stockService;

    @PatchMapping("/api/v1/products/{productId}/stock")
    public DecreaseStockResDto decreaseStock(@RequestBody DecreaseStockReqDto body, @PathVariable Long productId) {
        return stockService.decreaseStock(productId, body.quantity());
    }

}
