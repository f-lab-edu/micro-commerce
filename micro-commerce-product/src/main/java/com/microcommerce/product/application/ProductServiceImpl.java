package com.microcommerce.product.application;

import com.microcommerce.product.domain.dto.ApiResult;
import com.microcommerce.product.domain.dto.feign.res.ProfileResDto;
import com.microcommerce.product.domain.dto.res.CreateProductResDto;
import com.microcommerce.product.domain.entity.Product;
import com.microcommerce.product.domain.vo.CreateProductVo;
import com.microcommerce.product.exception.ProductException;
import com.microcommerce.product.exception.ProductExceptionCode;
import com.microcommerce.product.infrastructure.feign.MemberClient;
import com.microcommerce.product.infrastructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final MemberClient memberClient;

    @Override
    public CreateProductResDto createProduct(CreateProductVo data) {
        final ApiResult<ProfileResDto> profile = memberClient.getProfile(data.sellerId());
        if (!profile.isSuccess() || profile.getData() == null) {
            throw new ProductException(ProductExceptionCode.USER_NOT_FOUND);
        }

        final Product product = productRepository.save(Product.getInstance(data, profile.getData().name()));
        return CreateProductResDto.getInstance(product.getName(), product.getPrice(), product.getStock());
    }

}
