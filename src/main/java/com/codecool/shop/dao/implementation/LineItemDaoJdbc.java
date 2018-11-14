package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DbConnection;
import com.codecool.shop.orderData.LineItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LineItemDaoJdbc {

    public LineItemDaoJdbc() {
    }

    public void add(int orderId, int quantity, int productId){
        String query =
                "INSERT INTO lineitems(order_id, quantity, product_id) VALUES(?,?,?)";
        try{
            Connection connection = DbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, orderId);
            statement.setInt(2, quantity);
            statement.setInt(3, productId);
            statement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void remove(int product_id) {
        String query =
                "DELETE FROM products WHERE id=?";
        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, product_id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<LineItem> getAll(int orderId){
        String query =
                "SELECT * FROM lineitems WHERE order_id=?";
        List<LineItem> lineItems = new ArrayList<>();
        try{
            Connection connection = DbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                lineItems.add(new LineItem(
                        ProductDaoJdbc.getInstance().find(resultSet.getInt("product_id"))
                ));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return lineItems;
    }
}
