package com.microcommerce.order.application;

import com.microcommerce.order.domain.dao.ProductClientDao;
import com.microcommerce.order.domain.dto.feign.res.ProductResDto;
import com.microcommerce.order.domain.dto.res.CartProductResDto;
import com.microcommerce.order.domain.entity.Cart;
import com.microcommerce.order.domain.vo.AddCartVo;
import com.microcommerce.order.infrastructure.repository.CartRepository;
import com.microcommerce.order.mapper.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;

    private final CartMapper cartMapper;

    private final ProductClientDao productClientDao;

    public void addCart(final AddCartVo vo) {
        cartRepository.save(cartMapper.toCart(vo));
    }

    public List<CartProductResDto> getAllCartProducts(final Long userId) {
        final List<Cart> products = cartRepository.findByUserId(userId);
        final Map<Long, ProductResDto> productInfoMap = productClientDao.getProductMapByIds(
                products.stream()
                        .map(Cart::getProductId)
                        .toList()
        );
        return products.stream()
                .map(p -> cartMapper.toCartProductResDto(productInfoMap.get(p.getProductId()), p.getQuantity()))
                .toList();
    }

}
