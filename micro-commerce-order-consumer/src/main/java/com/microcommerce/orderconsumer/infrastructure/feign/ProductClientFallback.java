package com.microcommerce.orderconsumer.infrastructure.feign;

import com.microcommerce.orderconsumer.domain.dto.feign.req.DecreaseStockReqDto;
import com.microcommerce.orderconsumer.domain.dto.feign.res.DecreaseStockResDto;
import com.microcommerce.orderconsumer.domain.dto.feign.res.ProductResDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ProductClientFallback implements ProductClient {

    @Override
    public List<ProductResDto> getProducts(List<Long> ids) {
        log.error("getProduct failed");
        return null;
    }

    @Override
    public DecreaseStockResDto decreaseStock(Long productId, DecreaseStockReqDto body) {
        log.error("decreaseStock failed");
        return null;
    }
}
