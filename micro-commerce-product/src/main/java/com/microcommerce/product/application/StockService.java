package com.microcommerce.product.application;

import com.microcommerce.product.domain.dto.res.DecreaseStockResDto;
import com.microcommerce.product.domain.dto.res.SetStockResDto;
import com.microcommerce.product.domain.entity.Product;
import com.microcommerce.product.domain.enums.ProductStatus;
import com.microcommerce.product.exception.ProductException;
import com.microcommerce.product.exception.ProductExceptionCode;
import com.microcommerce.product.infrastructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class StockService {

    private final ProductRepository productRepository;

    @Transactional
    public DecreaseStockResDto decreaseStock(final Long productId, final Integer quantity) {
        final Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException(ProductExceptionCode.INTERNAL_REQUEST_ERROR));

        if (product.getStock() < quantity) {
            product.setStatus(ProductStatus.SOLD_OUT);
            throw new ProductException(ProductExceptionCode.EXCEED_STOCK);
        }

        product.decreaseStock(quantity);
        return new DecreaseStockResDto(true);
    }

    @Transactional
    public SetStockResDto setStock(final Long userId, final Long productId, final Integer quantity) {
        final Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException(ProductExceptionCode.INTERNAL_REQUEST_ERROR));
        if (!product.getSellerId().equals(userId)) {
            throw new ProductException(ProductExceptionCode.FORBIDDEN);
        }

        product.setStock(quantity);
        if (product.getStatus().equals(ProductStatus.SOLD_OUT)) {
            product.setStatus(ProductStatus.AVAILABLE);
        }

        return new SetStockResDto(true);
    }

}
