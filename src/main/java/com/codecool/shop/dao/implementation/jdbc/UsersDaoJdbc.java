package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.DbConnection;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.orderData.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsersDaoJdbc implements UserDao {

    private static UsersDaoJdbc instance = null;
    Connection connection;

    private UsersDaoJdbc() {
    }

    public static UsersDaoJdbc getInstance(){
        if (instance == null) {
            instance = new UsersDaoJdbc();
        }
        return instance;
    }

    @Override
    public void register(Customer customer) {
        getConnection();
        try {
            String query =
                    "INSERT INTO users(name, email, password, billing_address, shipping_address) VALUES (?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);

            String username = customer.getName();
            String email = customer.getEmail();
            String password = customer.getPassword();
            String billing = customer.getBillingAddress();
            String shipping = customer.getShippingAddress();

            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, billing);
            statement.setString(5, shipping);
            statement.execute();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean isUsernameTaken(String username) {
        return false;
    }

    @Override
    public String getHashByUsername(String username) {

        return null;
    }

    @Override
    public Customer getCostumerByUsername(String username) {
        return null;
    }

    private void getConnection(){
        try{
            this.connection = DbConnection.getConnection();
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("SQL Exception thrown");
        }
    }
}
