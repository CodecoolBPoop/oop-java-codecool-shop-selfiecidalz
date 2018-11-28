package com.codecool.shop.orderData;

public class Customer {

    private String name;
    private String email;
    private String billingAddress;
    private String shippingAddress;
    private String password;

    public Customer(String name, String email, String billingAddress, String shippingAddress) {
        this.name = name;
        this.email = email;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
