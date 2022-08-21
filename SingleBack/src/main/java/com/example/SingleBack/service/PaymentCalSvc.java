package com.example.SingleBack.service;

import com.example.SingleBack.dao.CustomerMapper;
import com.example.SingleBack.dao.ItemMapper;
import com.example.SingleBack.model.Customer;
import com.example.SingleBack.model.Item;
import com.example.SingleBack.model.ItemTotal;
import com.example.SingleBack.model.PaymentResult;
import com.taxjar.Taxjar;
import com.taxjar.exception.TaxjarException;
import com.taxjar.model.rates.RateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentCalSvc {
    @Autowired
    ItemMapper itemMapper;

    @Autowired
    CustomerMapper customerMapper;

    @Value("${APItoken}")
    String token;

    public List<Item> LoadItemIds(){
        return itemMapper.selectAll();
    }

    public PaymentResult calculatePayment(PaymentResult pr) throws TaxjarException {
        Taxjar client = new Taxjar(token);
        Customer cus = customerMapper.selectByPrimaryKey(pr.getId());
        if(cus == null){
            pr.setName("");
            return pr;
        }
        pr.setName(cus.getName());
        pr.setAddress(cus.getAddress());
        String[] addressInfo = cus.getAddress().split(" ");
        Map<String, String> params = new HashMap<>();
        params.put("country", addressInfo[0]);
        RateResponse res = client.ratesForLocation(addressInfo[1], params);
        BigDecimal taxRate = BigDecimal.valueOf(res.rate.getCombinedRate());
        BigDecimal sum = BigDecimal.ZERO;
        for(ItemTotal item:pr.getItems()) {
            Item itemOriginal = itemMapper.selectByPrimaryKey(item.getId());
            if(item.getQuantity() == null) item.setQuantity(BigDecimal.ZERO);
            item.setTotal(item.getQuantity().multiply(itemOriginal.getPrice()).setScale(2,RoundingMode.HALF_UP));
            item.setTax(item.getTotal().multiply(taxRate).setScale(2, RoundingMode.HALF_UP));
            sum = sum.add(item.getTotal());
            sum = sum.add(item.getTax());
        }
        pr.setTotal(sum);
        return pr;
    }
}
