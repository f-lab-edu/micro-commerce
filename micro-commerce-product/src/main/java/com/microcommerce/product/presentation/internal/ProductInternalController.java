package com.microcommerce.product.presentation.internal;

import com.microcommerce.product.application.ProductService;
import com.microcommerce.product.application.StockService;
import com.microcommerce.product.domain.dto.req.DecreaseStockReqDto;
import com.microcommerce.product.domain.dto.res.ProductResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ProductInternalController {

    private final ProductService productService;

    private final StockService stockService;

    @GetMapping("/internal-api/v1/products")
    public List<ProductResDto> getProductsByIds(@RequestParam(name = "ids") final List<Long> ids) {
        return productService.getProductsByIds(ids);
    }

    @PostMapping("/internal-api/v1/products/{productId}/stock")
    public String changeStock(@PathVariable final Long productId, @RequestBody final Integer change) {
        return stockService.changeStock(productId, change);
    }

}
