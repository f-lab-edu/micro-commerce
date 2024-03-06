package com.microcommerce.product.application;

import com.microcommerce.product.domain.dto.feign.res.ProfileResDto;
import com.microcommerce.product.domain.dto.res.CreateProductResDto;
import com.microcommerce.product.domain.dto.res.ProductDetailResDto;
import com.microcommerce.product.domain.dto.res.ProductResDto;
import com.microcommerce.product.domain.entity.Product;
import com.microcommerce.product.domain.entity.ProductImage;
import com.microcommerce.product.domain.vo.CreateProductVo;
import com.microcommerce.product.exception.ProductException;
import com.microcommerce.product.exception.ProductExceptionCode;
import com.microcommerce.product.infrastructure.feign.MemberClient;
import com.microcommerce.product.infrastructure.repository.ProductImageRepository;
import com.microcommerce.product.infrastructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductImageRepository productImageRepository;

    private final MemberClient memberClient;

    public CreateProductResDto createProduct(CreateProductVo data) {
        // TODO: feign 응답값을 Optional로 받아야하는지
        final ProfileResDto profile = memberClient.getProfile(data.sellerId());
        if (profile == null) {
            throw new ProductException(ProductExceptionCode.USER_NOT_FOUND);
        }

        final Product product = productRepository.save(Product.getInstance(data, profile.name()));
        return CreateProductResDto.getInstance(product.getName(), product.getPrice(), product.getStock());
    }

    public List<ProductResDto> getProducts(final List<Long> ids) {
        return productRepository.getProducts(ids);
    }

    public ProductDetailResDto getProduct(final Long productId) {
        return productRepository.findById(productId)
                .map(p -> {
                    final List<ProductImage> images = productImageRepository.findAllByProductIdOrderByDisplayOrder(productId);
                    return ProductDetailResDto.getInstance(p, images);
                })
                .orElseThrow(() -> new ProductException(ProductExceptionCode.UNAUTHORIZED));
    }

}
