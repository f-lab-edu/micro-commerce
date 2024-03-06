package com.microcommerce.orderconsumer.application;

import com.microcommerce.orderconsumer.domain.dto.feign.res.ProductResDto;
import com.microcommerce.orderconsumer.domain.vo.OrderDetailVo;
import com.microcommerce.orderconsumer.domain.vo.OrderVo;
import com.microcommerce.orderconsumer.infrastructure.feign.ProductClient;
import com.microcommerce.orderconsumer.infrastructure.repository.OrderDetailRepository;
import com.microcommerce.orderconsumer.infrastructure.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderDetailRepository orderDetailRepository;

    private final ProductClient productClient;

    public void order(final OrderVo vo) {
        final List<ProductResDto> product = productClient.getProducts(
                vo.products().stream().map(OrderDetailVo::productId).toList()
        );

        /*
        TODO: 진행중
            1. 재고 수량
            2. 셀러 정보
         */
        product.forEach(p -> log.info(p.toString()));
    }


}
