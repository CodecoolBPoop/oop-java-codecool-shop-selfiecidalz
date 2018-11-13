package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DbConnection;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJdbc implements ProductCategoryDao {

    @Override
    public void add(ProductCategory category) {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement add = connection.prepareStatement("INSERT INTO categories (name, department, description) VALUES (?, ?, ?);")
        ){
            add.setString(1, category.getName());
            add.setString(2, category.getDepartment());
            add.setString(3, category.getDescription());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ProductCategory find(int id) {
        String addQuery = "SELECT name, department, description FROM categories WHERE id=?;";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement add = connection.prepareStatement(addQuery)
        ){
            add.setInt(1, id);

            ResultSet resultSet = add.executeQuery();
            if (resultSet.next()) {
                ProductCategory result = new ProductCategory(resultSet.getString("name"),
                        resultSet.getString("department"),
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
            PreparedStatement remove = connection.prepareStatement("DELETE FROM categories WHERE id=?;")
        ){
            remove.setInt(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ProductCategory> getAll() {
        String getAll = "SELECT * FROM categories";
        List<ProductCategory> result = new ArrayList<>();
        try (Connection connection = DbConnection.getConnection();
            PreparedStatement getAllCategories = connection.prepareStatement(getAll)
        ){
            ResultSet resultSet = getAllCategories.executeQuery();
            while(resultSet.next()) {
                ProductCategory pc = new ProductCategory(resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description"));
                result.add(pc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
