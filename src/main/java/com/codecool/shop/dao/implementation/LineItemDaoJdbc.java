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

    public void add(int orderId, int quantity, int productId) {
        String checkQuery = "SELECT IF EXISTS(SELECT * FROM lineitems WHERE order_id=? AND product_id=?";
        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement check = connection.prepareStatement(checkQuery);
            check.setInt(1, orderId);
            check.setInt(2, productId);
            ResultSet resultSet = check.executeQuery();
            if (resultSet.getBoolean("exists")) {
                PreparedStatement update = connection.prepareStatement("UPDATE lineitems SET quantity=? WHERE order_id=? AND product_id=?");
                update.setInt(1, quantity);
                update.setInt(2, orderId);
                update.setInt(3, productId);
                update.execute();
            } else {
                String query =
                        "INSERT INTO lineitems(order_id, quantity, product_id) VALUES(?,?,?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, orderId);
                statement.setInt(2, quantity);
                statement.setInt(3, productId);
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(int product_id) {
        String query =
                "DELETE FROM lineitems WHERE id=?";
        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, product_id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<LineItem> getAll(int orderId) {
        String query =
                "SELECT * FROM lineitems WHERE order_id=?";
        List<LineItem> lineItems = new ArrayList<>();
        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                lineItems.add(new LineItem(
                        ProductDaoJdbc.getInstance().find(resultSet.getInt("product_id"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lineItems;
    }
}
