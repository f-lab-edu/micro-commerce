package com.microcommerce.order.infrastructure.feign;

import com.microcommerce.order.domain.dto.feign.res.ProductResDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Primary
@FeignClient(name = "micro-commerce-product", fallbackFactory = ProductClientFallbackFactory.class)
public interface ProductClient {

    @GetMapping(value = "/internal-api/v1/products")
    ResponseEntity<List<ProductResDto>> getProducts(@RequestParam("ids") List<Long> productId);

}
