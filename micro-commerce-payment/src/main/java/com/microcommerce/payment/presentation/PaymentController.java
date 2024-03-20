package com.microcommerce.payment.presentation;

import com.microcommerce.payment.application.PaymentService;
import com.microcommerce.payment.domain.dto.req.PaymentReqDto;
import com.microcommerce.payment.util.HeaderCheckUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/api/v1/payments")
    public void inout(@RequestHeader final HttpHeaders header, @RequestBody final PaymentReqDto req) {
        if (req.price() == 0) {
            return;
        }
        HeaderCheckUtil.checkUserId(header, req.userId());
        paymentService.inout(req.userId(), req.price(), req.type(), req.txId());
    }

}
