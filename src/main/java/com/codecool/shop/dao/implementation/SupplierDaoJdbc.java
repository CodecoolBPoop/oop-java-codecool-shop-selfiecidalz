package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DbConnection;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao {

    @Override
    public void add(Supplier supplier) {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement addSupplier = connection.prepareStatement("INSERT INTO suppliers (name, description) VALUES (?, ?);")
            ){
             addSupplier.setString(1, supplier.getName());
             addSupplier.setString(2, supplier.getDescription());
             addSupplier.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Supplier find(int id) {
        String addQuery = "SELECT name, department, description FROM suppliers WHERE id=?;";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement add = connection.prepareStatement(addQuery)
        ){
            add.setInt(1, id);

            ResultSet resultSet = add.executeQuery();
            if (resultSet.next()) {
                Supplier result = new Supplier(resultSet.getString("name"),
                        resultSet.getString("description"));
                return result;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement remove = connection.prepareStatement("DELETE FROM suppliers WHERE id=?;")
        ){
            remove.setInt(1, id);
            remove.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Supplier> getAll() {
        String getAll = "SELECT * FROM suppliers";
        List<Supplier> result = new ArrayList<>();
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement getAllCategories = connection.prepareStatement(getAll)
        ){
            ResultSet resultSet = getAllCategories.executeQuery();
            while(resultSet.next()) {
                result.add(new Supplier(resultSet.getString("name"),
                        resultSet.getString("description")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }
}
