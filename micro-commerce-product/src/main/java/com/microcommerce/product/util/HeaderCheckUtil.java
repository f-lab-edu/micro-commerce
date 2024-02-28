package com.microcommerce.product.util;

import com.microcommerce.product.exception.ProductException;
import com.microcommerce.product.exception.ProductExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

@Slf4j
public class HeaderCheckUtil {

    public static void checkUserId(final HttpHeaders headers, final long userId) {
        final long headerUserId;
        try {
            final String headerUserIdStr = headers.getFirst("x-user-id");
            if (headerUserIdStr == null) {
                throw new ProductException(ProductExceptionCode.UNAUTHORIZED);
            }

            headerUserId = Long.parseLong(headerUserIdStr);
        } catch (NumberFormatException e) {
            log.error(e.getMessage());
            throw new ProductException(ProductExceptionCode.UNAUTHORIZED);
        }

        if (userId != headerUserId) {
            throw new ProductException(ProductExceptionCode.FORBIDDEN);
        }
    }

}
