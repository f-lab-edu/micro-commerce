package com.microcommerce.order.presentation;

import com.microcommerce.order.application.CartService;
import com.microcommerce.order.domain.dto.req.AddCartReqDto;
import com.microcommerce.order.domain.dto.res.CartProductResDto;
import com.microcommerce.order.mapper.CartMapper;
import com.microcommerce.order.util.HeaderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CartController {

    private final CartService cartService;

    private final CartMapper cartMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/orders/carts")
    public void addCart(@RequestBody final AddCartReqDto req) {
        cartService.addCart(cartMapper.toAddCartVo(req));
    }

    @GetMapping("/api/v1/orders/carts")
    public List<CartProductResDto> getAllCartProducts(@RequestHeader final HttpHeaders header) {
        final Long userId = HeaderUtil.getHeaderUserId(header);
        return cartService.getAllCartProducts(userId);
    }

    @PostMapping("/api/v1/orders/carts/{cartId}/quantity")
    public void updateCartProductQuantity(@RequestHeader final HttpHeaders header,
                                          @PathVariable final Long cartId,
                                          @RequestBody final Integer quantity) {
        final Long userId = HeaderUtil.getHeaderUserId(header);
        cartService.updateCartProductQuantity(userId, cartId, quantity);
    }

    @DeleteMapping("/api/v1/orders/carts/{cartId}")
    public void deleteCartProduct(@RequestHeader final HttpHeaders header,
                                  @PathVariable final Long cartId) {
        final Long userId = HeaderUtil.getHeaderUserId(header);
        cartService.deleteCartProduct(userId, cartId);
    }

}
