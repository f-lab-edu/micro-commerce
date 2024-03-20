package com.microcommerce.product.application;

import com.microcommerce.product.domain.dao.MemberClientDao;
import com.microcommerce.product.domain.dto.feign.res.ProfileResDto;
import com.microcommerce.product.domain.dto.res.CreateProductResDto;
import com.microcommerce.product.domain.dto.res.ProductDetailResDto;
import com.microcommerce.product.domain.dto.res.ProductResDto;
import com.microcommerce.product.domain.entity.Product;
import com.microcommerce.product.domain.entity.ProductImage;
import com.microcommerce.product.domain.vo.CreateProductVo;
import com.microcommerce.product.exception.ProductException;
import com.microcommerce.product.exception.ProductExceptionCode;
import com.microcommerce.product.infrastructure.repository.ProductImageRepository;
import com.microcommerce.product.infrastructure.repository.ProductRepository;
import com.microcommerce.product.mapper.ProductMapper;
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

    private final MemberClientDao memberClientDao;

    private final ProductMapper productMapper;

    public CreateProductResDto createProduct(final CreateProductVo data) {
        final ProfileResDto profile = memberClientDao.getProfile(data.sellerId());
        final Product product = productRepository.save(productMapper.toProduct(data, profile.name()));
        return productMapper.toCreateProductResDto(product);
    }

    public List<ProductResDto> getProducts() {
        return productRepository.findAll().stream().map(p -> {
            final ProductImage representativeImage = productImageRepository.findFirstByProductIdOrderByDisplayOrder(p.getId());
            return productMapper.toProductResDto(p, representativeImage.getUrl());
        }).toList();
    }

    public List<ProductResDto> getProductsByIds(final List<Long> ids) {
        return productRepository.getProducts(ids);
    }

    public ProductDetailResDto getProduct(final Long productId) {
        return productRepository.findById(productId)
                .map(p -> {
                    final List<String> images = productImageRepository.findAllByProductIdOrderByDisplayOrder(productId).stream()
                            .map(ProductImage::getUrl)
                            .toList();
                    return productMapper.toProductDetailResDto(p, images);
                })
                .orElseThrow(() -> new ProductException(ProductExceptionCode.UNAUTHORIZED));
    }

}
