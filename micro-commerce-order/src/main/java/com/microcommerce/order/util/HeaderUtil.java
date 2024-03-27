package com.microcommerce.order.util;

import com.microcommerce.order.exception.OrderException;
import com.microcommerce.order.exception.OrderExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

@Slf4j
public class HeaderUtil {

    public static Long getHeaderUserId(final HttpHeaders headers) {
        final long headerUserId;
        try {
            final String headerUserIdStr = headers.getFirst("x-user-id");
            if (headerUserIdStr == null) {
                throw new OrderException(OrderExceptionCode.UNAUTHORIZED);
            }

            return Long.parseLong(headerUserIdStr);
        } catch (NumberFormatException e) {
            log.error(e.getMessage());
            throw new OrderException(OrderExceptionCode.UNAUTHORIZED);
        }
    }

}
