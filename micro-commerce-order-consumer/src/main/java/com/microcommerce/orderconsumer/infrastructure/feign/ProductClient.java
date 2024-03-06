package com.microcommerce.orderconsumer.infrastructure.feign;

import com.microcommerce.orderconsumer.domain.dto.feign.res.ProductResDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Primary
@FeignClient(name = "micro-commerce-product", fallbackFactory = ProductClientFallbackFactory.class)
public interface ProductClient {

    @GetMapping(value = "/public-api/v1/products")
    List<ProductResDto> getProducts(@RequestParam("ids") List<Long> productId);

}
