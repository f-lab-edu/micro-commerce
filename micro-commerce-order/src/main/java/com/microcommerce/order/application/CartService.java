package com.microcommerce.order.application;

import com.microcommerce.order.domain.dao.ProductClientDao;
import com.microcommerce.order.domain.dto.feign.res.ProductResDto;
import com.microcommerce.order.domain.dto.res.CartProductResDto;
import com.microcommerce.order.domain.entity.Cart;
import com.microcommerce.order.domain.vo.AddCartVo;
import com.microcommerce.order.exception.OrderException;
import com.microcommerce.order.exception.OrderExceptionCode;
import com.microcommerce.order.infrastructure.repository.CartRepository;
import com.microcommerce.order.mapper.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void updateCartProductQuantity(final Long userId, final Long cartId, final Integer quantity) {
        cartRepository.findById(cartId)
                .filter(c -> userId.equals(c.getUserId()))
                .map(c -> {
                    c.setQuantity(quantity);
                    return c;
                })
                .orElseThrow(() -> new OrderException(OrderExceptionCode.CART_PRODUCT_NOT_FOUND));
    }

    public void deleteCartProduct(final Long userId, final Long cartId) {
        cartRepository.findById(cartId)
                .filter(c -> userId.equals(c.getUserId()))
                .map(c -> {
                    cartRepository.delete(c);
                    return c;
                })
                .orElseThrow(() -> new OrderException(OrderExceptionCode.CART_PRODUCT_NOT_FOUND));
    }

}
