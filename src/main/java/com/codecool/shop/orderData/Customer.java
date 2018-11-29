package com.codecool.shop.orderData;

public class Customer {
    private int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", billingAddress='" + billingAddress + '\'' +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
