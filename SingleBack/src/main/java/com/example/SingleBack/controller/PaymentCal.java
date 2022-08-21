package com.example.SingleBack.controller;

import com.example.SingleBack.model.PaymentResult;
import com.example.SingleBack.service.PaymentCalSvc;
import com.taxjar.exception.TaxjarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaymentCal {

    @Autowired
    PaymentCalSvc paymentCalSvc;

    @PostMapping("/get_result")
    public PaymentResult getPaymentResult(@RequestBody PaymentResult result) throws TaxjarException {
        System.out.println(result);
        return paymentCalSvc.calculatePayment((PaymentResult)result);
    }

    @GetMapping("/load_items")
    public List<?> getItems() throws TaxjarException {
        return paymentCalSvc.LoadItemIds();
    }

}
