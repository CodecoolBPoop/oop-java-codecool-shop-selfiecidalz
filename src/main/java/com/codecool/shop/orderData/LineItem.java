package com.codecool.shop.orderData;

import com.codecool.shop.model.Product;

public class LineItem {
    private String productName;
    private int quantity = 0;
    private double unitPrice;
    private double subTotalPrice;
    private Product product;


    public LineItem(Product product) {
        this.productName = product.getName();
        this.unitPrice = product.getDefaultPrice();
        this.subTotalPrice = unitPrice;
        this.product = product;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setQuantity(){
        this.quantity++;
        subTotalPrice += unitPrice;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
        subTotalPrice = quantity * unitPrice;
    }

    public double getSubTotalPrice() {
        return subTotalPrice;
    }

    public boolean compareProductId(int id) {
        return product.getId() == id;
    }
}
