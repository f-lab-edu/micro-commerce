package com.microcommerce.product.application;

import com.microcommerce.product.domain.dto.res.DecreaseStockResDto;
import com.microcommerce.product.domain.entity.Product;
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
    public DecreaseStockResDto decreaseStock(Long productId, Integer quantity) {
        // FIXME: 람다식 변환
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException(ProductExceptionCode.INTERNAL_REQUEST_ERROR));

        if (product.getStock() < quantity) {
            throw new ProductException(ProductExceptionCode.EXCEED_STOCK);
        }

        product.decreaseStock(quantity);
        return new DecreaseStockResDto(true);
    }

}
