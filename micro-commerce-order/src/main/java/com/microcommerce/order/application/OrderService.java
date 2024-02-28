package com.microcommerce.order.application;

import com.microcommerce.order.domain.vo.OrderVo;

public interface OrderService {

    void order(OrderVo data);

}
