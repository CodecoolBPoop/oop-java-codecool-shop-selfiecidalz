package com.codecool.shop.dao.implementation.jdbc;

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

    private static ProductCategoryDaoJdbc instance = null;

    private ProductCategoryDaoJdbc() {
    }

    public static ProductCategoryDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category) {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement add = connection.prepareStatement("INSERT INTO categories (name, department, description) VALUES (?, ?, ?);")
        ){
            add.setString(1, category.getName());
            add.setString(2, category.getDepartment());
            add.setString(3, category.getDescription());
            add.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ProductCategory find(int id) {
        String addQuery = "SELECT id, name, department, description FROM categories WHERE id=?;";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement find = connection.prepareStatement(addQuery)
        ){
            find.setInt(1, id);

            ResultSet resultSet = find.executeQuery();
            if (resultSet.next()) {
                ProductCategory result = new ProductCategory(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description"));
                connection.close();
                resultSet.close();
                find.close();
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
            remove.execute();
            connection.close();
            remove.close();
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
                ProductCategory pc = new ProductCategory(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description"));
                result.add(pc);
            }
            connection.close();
            getAllCategories.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int getIdByName(String name) {
        String getName = "SELECT id FROM categories WHERE name=?";
        try (Connection connection = DbConnection.getConnection();
            PreparedStatement getItByName = connection.prepareStatement(getName)
        ) {
            getItByName.setString(1, name);
            ResultSet resultSet = getItByName.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                connection.close();
                getItByName.close();
                resultSet.close();
                return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
