package com.codecool.shop.dao;

import com.codecool.shop.orderData.Customer;

public interface UserDao {

    void register(Customer customer);
    boolean isUsernameTaken(String username);
    String getHashByUsername(String username);
    Customer getCostumerByUsername(String username);

}
