package com.microcommerce.product.presentation;

import com.microcommerce.product.application.ProductService;
import com.microcommerce.product.domain.dto.req.CreateProductReqDto;
import com.microcommerce.product.domain.dto.res.CreateProductResDto;
import com.microcommerce.product.domain.dto.res.ProductDetailResDto;
import com.microcommerce.product.domain.dto.res.ProductResDto;
import com.microcommerce.product.mapper.ProductMapper;
import com.microcommerce.product.util.HeaderCheckUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    private final ProductMapper productMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/products")
    public CreateProductResDto createProduct(@RequestHeader final HttpHeaders header,
                                             @RequestBody final CreateProductReqDto req) {
        HeaderCheckUtil.checkUserId(header, req.sellerId());
        return productService.createProduct(productMapper.toCreateProductVo(req));
    }

    // TODO: 페이징 처리
    @GetMapping("/public-api/v1/products")
    public List<ProductResDto> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/public-api/v1/products/{productId}")
    public ProductDetailResDto getProduct(@PathVariable final Long productId) {
        return productService.getProduct(productId);
    }

}
