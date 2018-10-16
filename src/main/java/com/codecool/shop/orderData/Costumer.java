package com.codecool.shop.orderData;

public class Costumer {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String billingAdress;
    private String shippingAdress;

    public Costumer(String firstName, String lastName, String email, String phoneNumber,
                    String billingAdress, String shippingAdress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.billingAdress = billingAdress;
        this.shippingAdress = shippingAdress;
    }

}
