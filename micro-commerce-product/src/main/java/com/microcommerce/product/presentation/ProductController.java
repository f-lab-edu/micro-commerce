package com.microcommerce.product.presentation;

import com.microcommerce.product.application.ProductService;
import com.microcommerce.product.domain.dto.ApiResult;
import com.microcommerce.product.domain.dto.req.CreateProductReqDto;
import com.microcommerce.product.domain.dto.res.CreateProductResDto;
import com.microcommerce.product.domain.vo.CreateProductVo;
import com.microcommerce.product.util.HeaderCheckUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ProductController {


    private final ProductService productService;

    @PostMapping("/api/v1/products")
    public ResponseEntity<ApiResult<CreateProductResDto>> createProduct(@RequestHeader final HttpHeaders header,
                                                                        @RequestBody final CreateProductReqDto req) {
        HeaderCheckUtil.checkUserId(header, req.sellerId());

        final CreateProductVo data = CreateProductVo.getInstance(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResult.success(productService.createProduct(data)));
    }

}
