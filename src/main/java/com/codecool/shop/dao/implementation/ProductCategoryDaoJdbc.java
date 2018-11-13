package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DbConnection;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ProductCategoryDaoJdbc implements ProductCategoryDao {

    @Override
    public void add(ProductCategory category) {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement insert = connection.prepareStatement("INSERT INTO categories (name, department, description) VALUES (?,?,?);")
        ){
            insert.setString(1, category.getName());
            insert.setString(2, category.getDepartment());
            insert.setString(3, category.getDescription());
            insert.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProductCategory find(int id) {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement find = connection.prepareStatement("SELECT * FROM categories WHERE id=?;")
        ) {
            find.setInt(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        try (Connection connection = DbConnection.getConnection();
             Statement getAll = connection.createStatement()
        ){
            getAll.execute("SELECT * FROM categories;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
