package com.microcommerce.payment.presentation;

import com.microcommerce.payment.application.PaymentService;
import com.microcommerce.payment.domain.dto.req.PaymentReqDto;
import com.microcommerce.payment.util.HeaderCheckUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PaymentInternalController {

    private final PaymentService paymentService;

    @PostMapping("/internal-api/v1/payments")
    public void inout(@RequestHeader final HttpHeaders header, @RequestBody final PaymentReqDto req) {
        if (req.price() == 0) {
            return;
        }
        HeaderCheckUtil.checkUserId(header, req.userId());
        paymentService.inout(req.userId(), req.price(), req.type(), req.txId());
    }

    @GetMapping("/internal-api/v1/payments/balance")
    public int getBalance(@RequestHeader final HttpHeaders header, @RequestParam("user") final Long userId) {
        HeaderCheckUtil.checkUserId(header, userId);
        return paymentService.getBalance(userId);
    }

}
