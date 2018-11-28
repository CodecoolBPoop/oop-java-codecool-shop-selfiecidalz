package com.codecool.shop.orderData;

public class Costumer {

    private String name;
    private String email;
    private String billingAddress;
    private String shippingAddress;
    private static Costumer instance;

    public Costumer(String name,  String email, String billingAddress, String shippingAddress) {
        this.name = name;
        this.email = email;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }


    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
