package com.example.SingleBack.service;

import com.example.SingleBack.dao.CustomerMapper;
import com.example.SingleBack.dao.ItemMapper;
import com.example.SingleBack.model.Customer;
import com.example.SingleBack.model.Item;
import com.example.SingleBack.model.ItemTotal;
import com.example.SingleBack.model.PaymentResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentCalSvc {
    @Autowired
    ItemMapper itemMapper;

    @Autowired
    CustomerMapper customerMapper;

    public List<ItemTotal> LoadItemIds(){
        return null;
    }

    public PaymentResult calculatePayment(PaymentResult pr){
//        Taxjar client = new Taxjar("YOUR API TOKEN");
//        Map<String, String> params = new HashMap<>();
//        params.put("country", "US");
//        params.put("city", "Watts");
//        params.put("street", "123 Test St");
//        RateResponse res = client.ratesForLocation("90002", params);

        Customer cus = customerMapper.selectByPrimaryKey(pr.getId());
        pr.setName(cus.getName());
        pr.setAddress(cus.getAddress());
        BigDecimal taxRate = BigDecimal.valueOf(0.13);
        BigDecimal sum = BigDecimal.ZERO;
        for(ItemTotal item:pr.getItems()) {
            Item itemOriginal = itemMapper.selectByPrimaryKey(item.getId());
            item.setTotal(item.getQuantity().multiply(itemOriginal.getPrice()));
            item.setTax(item.getTotal().multiply(taxRate));
            sum.add(item.getTotal());
            sum.add(item.getTax());
        }
        pr.setTotal(sum);
        return pr;
    }
}
