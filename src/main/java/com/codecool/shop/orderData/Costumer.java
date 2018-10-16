package com.codecool.shop.orderData;

public class Costumer {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String billingAddress;
    private String shippingAddress;

    public Costumer(String firstName, String lastName, String email, String phoneNumber,
                    String billingAddress, String shippingAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }

}
