package com.example.SingleBack.model;

import java.math.BigDecimal;
import java.util.List;

public class PaymentResult extends Customer{
    private BigDecimal total;
    private List<ItemTotal> items;

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<ItemTotal> getItems() {
        return items;
    }

    public void setItems(List<ItemTotal> items) {
        this.items = items;
    }
}