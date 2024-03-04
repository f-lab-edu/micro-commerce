package com.microcommerce.product.presentation;

import com.microcommerce.product.application.ProductService;
import com.microcommerce.product.domain.dto.req.CreateProductReqDto;
import com.microcommerce.product.domain.dto.res.CreateProductResDto;
import com.microcommerce.product.domain.vo.CreateProductVo;
import com.microcommerce.product.util.HeaderCheckUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ProductController {


    private final ProductService productService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/products")
    public CreateProductResDto createProduct(@RequestHeader final HttpHeaders header,
                                             @RequestBody final CreateProductReqDto req) {
        HeaderCheckUtil.checkUserId(header, req.sellerId());
        return productService.createProduct(CreateProductVo.getInstance(req));
    }

}
