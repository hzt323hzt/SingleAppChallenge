package com.example.SingleBack.controller;

import com.example.SingleBack.model.PaymentResult;
import com.example.SingleBack.service.PaymentCalSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentCal {

    @Autowired
    PaymentCalSvc paymentCalSvc;

    @PostMapping("/results")
    public PaymentResult getPaymentResult(@RequestBody PaymentResult result) {
        return paymentCalSvc.calculatePayment(result);
    }

}
