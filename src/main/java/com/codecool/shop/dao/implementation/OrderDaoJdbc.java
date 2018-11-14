package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DbConnection;
import com.codecool.shop.orderData.LineItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.*;
import java.util.List;

public class OrderDaoJdbc {

    private Date date;
    private static LineItemDaoJdbc lineItemDaoJdbc = new LineItemDaoJdbc();
    private static OrderDaoJdbc instance = null;
    private int id;

    private OrderDaoJdbc(int id) {
        this.date = new Date(System.currentTimeMillis());
        this.id = id;
    }

    public static OrderDaoJdbc getInstance() {
        if(instance == null)
            instance = new OrderDaoJdbc(1);
        return instance;
    }

    /*public void deleteOrder(int id) {
        String deleteQuery = "REMOVE FROM orders WHERE id=?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteQuery))
        {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<LineItem> getCartList(int id) {
        String getCartQuery = "SELECT * FROM lineitems WHERE order_id=?";
        List<LineItem> result = new ArrayList<>();
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(getCartQuery))
        {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                result.add(new LineItem(ProductDaoJdbc.getInstance().find(resultSet.getInt("product_id"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void addToCartList(int orderId, int quantity, int productId) {
        lineItemDaoJdbc.add(orderId, quantity, productId);
    }

    public void removeFromCartList(){

    }*/

    public void add(int userId, int total, Date date) {
        String addQuery = "INSERT INTO orders (user_id, total, date) VALUES (?, ?, ?)";
        try (Connection connection = DbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(addQuery))
        {
            statement.setInt(1, userId);
            statement.setInt(2, total);
            statement.setDate(3, new Date(System.currentTimeMillis()));
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
