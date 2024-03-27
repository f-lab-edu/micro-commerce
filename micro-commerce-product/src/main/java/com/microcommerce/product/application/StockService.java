package com.microcommerce.product.application;

import com.microcommerce.product.domain.dto.res.SetStockResDto;
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
    public String changeStock(final Long productId, final Integer change) {
        return productRepository.findById(productId)
                .map(p -> {
                    if (p.getStock() < change) {
                        throw new ProductException(ProductExceptionCode.INSUFFICIENT_STOCK);
                    }
                    p.setStock(p.getStock() + change);
                    if (p.getStock() == 0) {
                        p.setStatus(ProductStatus.SOLD_OUT);
                    }
                    return "SUCCESS";
                })
                .orElseThrow(() -> new ProductException(ProductExceptionCode.PRODUCT_NOT_FOUND));
    }

    @Transactional
    public SetStockResDto setStock(final Long userId, final Long productId, final Integer quantity) {
        return productRepository.findById(productId)
                .map(p -> {
                    if (!userId.equals(p.getSellerId())) {
                        throw new ProductException(ProductExceptionCode.FORBIDDEN);
                    }
                    p.setStock(quantity);
                    if (p.getStatus() == ProductStatus.SOLD_OUT && quantity > 0) {
                        p.setStatus(ProductStatus.AVAILABLE);
                    } else if (quantity == 0) {
                        p.setStatus(ProductStatus.SOLD_OUT);
                    }
                    return new SetStockResDto(true);
                })
                .orElseThrow(() -> new ProductException(ProductExceptionCode.PRODUCT_NOT_FOUND));
    }

}
