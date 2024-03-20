package com.microcommerce.payment.util;

import com.microcommerce.payment.exception.PaymentException;
import com.microcommerce.payment.exception.PaymentExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

@Slf4j
public class HeaderCheckUtil {

    public static void checkUserId(final HttpHeaders headers, final long userId) {
        final long headerUserId;
        try {
            final String headerUserIdStr = headers.getFirst("x-user-id");
            if (headerUserIdStr == null) {
                throw new PaymentException(PaymentExceptionCode.UNAUTHORIZED);
            }

            headerUserId = Long.parseLong(headerUserIdStr);
        } catch (NumberFormatException e) {
            log.error(e.getMessage());
            throw new PaymentException(PaymentExceptionCode.UNAUTHORIZED);
        }

        if (userId != headerUserId) {
            throw new PaymentException(PaymentExceptionCode.FORBIDDEN);
        }
    }

}
