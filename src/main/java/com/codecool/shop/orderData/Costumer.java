package com.codecool.shop.orderData;

public class Costumer {

    private String name;
    private String email;
    private String billingAddress;
    private String shippingAddress;
    private static Costumer instance;

    private Costumer(String name,  String email, String billingAddress, String shippingAddress) {
        this.name = name;
        this.email = email;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }

    public static Costumer getInstance(String name, String email, String billingAddress, String shippingAddress) {
        if(instance == null)
            instance = new Costumer(name, email, billingAddress, shippingAddress);
        return instance;
    }
}
