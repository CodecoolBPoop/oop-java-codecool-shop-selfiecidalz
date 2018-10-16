package com.codecool.shop.orderData;

import com.codecool.shop.model.Product;

import java.util.Date;
import java.util.List;

public class Order {
    private static int counter = 0;
    private int id;
    private List<Product> productList;
    private double total;
    private Costumer costumer;
    private Date date;
    private static Order instance = null;

    private Order(Product product) {
        this.id = counter++;
        addToProductList(product);
        this.date = new Date();
    }

    public static Order getInstance(Product product) {
        if(instance == null)
            instance = new Order(product);
        return instance;
    }

    public void addToProductList(Product product){
        productList.add(product);
        setTotal();
    }

    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }

    public void setTotal() {
        this.total = productList.stream().map(product -> product.getDefaultPrice()).reduce((x, y) -> x + y).get();
    }
}
