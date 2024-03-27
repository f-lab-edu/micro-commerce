package com.microcommerce.order.domain.dao;

import com.microcommerce.order.domain.dto.feign.res.ProductResDto;
import com.microcommerce.order.exception.OrderException;
import com.microcommerce.order.exception.OrderExceptionCode;
import com.microcommerce.order.infrastructure.feign.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ProductClientDao {

    private final ProductClient productClient;

    public Map<Long, ProductResDto> getProductMapByIds(final List<Long> productIds) {
        final ResponseEntity<List<ProductResDto>> productInfoRes = productClient.getProducts(productIds);
        if (!productInfoRes.getStatusCode().is2xxSuccessful() || productInfoRes.getBody() == null) {
            throw new OrderException(OrderExceptionCode.PRODUCT_NOT_FOUND);
        }

        return productInfoRes.getBody()
                .stream()
                .collect(Collectors.toMap(ProductResDto::id, p -> p));
    }

}
