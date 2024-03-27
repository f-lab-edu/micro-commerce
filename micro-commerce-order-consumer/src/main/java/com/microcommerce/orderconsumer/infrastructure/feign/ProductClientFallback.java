package com.microcommerce.orderconsumer.infrastructure.feign;

import com.microcommerce.orderconsumer.domain.dto.feign.res.ProductResDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ProductClientFallback implements ProductClient {

    @Override
    public ResponseEntity<List<ProductResDto>> getProducts(List<Long> ids) {
        log.error("getProduct failed");
        return null;
    }

    @Override
    public ResponseEntity<String> changeStock(Long productId, int change) {
        log.error("decreaseStock failed");
        return null;
    }
}
