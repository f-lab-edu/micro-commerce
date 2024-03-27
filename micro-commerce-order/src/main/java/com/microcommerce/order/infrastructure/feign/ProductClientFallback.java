package com.microcommerce.order.infrastructure.feign;

import com.microcommerce.order.domain.dto.feign.res.ProductResDto;
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

}
