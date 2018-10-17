package com.codecool.shop.orderData;

import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private List<LineItem> productList = new ArrayList<>();
    private double total;
    private Costumer costumer;
    private Date date;
    private static Order instance = null;

    private Order() {
        this.date = new Date();
    }

    public static Order getInstance() {
        if(instance == null)
            instance = new Order();
        return instance;
    }

    public void addToProductList(Product product){
        for (LineItem lineItem: productList) {
            if (lineItem.compareProductId(product.getId())) {
                lineItem.setQuantity();
                setTotal();
                return;
            }
        }
        productList.add(new LineItem(product));
        setTotal();
    }

    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }

    public void setTotal() {
        this.total = productList.stream().map(product -> product.getSubTotalPrice()).reduce((x, y) -> x + y).get();
    }
}
