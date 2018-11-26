package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DbConnection;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {

    private static ProductDaoJdbc instance = null;

    private ProductDaoJdbc() {
    }

    public static ProductDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductDaoJdbc();
        }
        return instance;
    }


    @Override
    public void add(Product product){
        try {
            String query =
                "INSERT INTO products(name, price, description, supplier_id, category_id, currency_id, image_path) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement statement = getConnection().prepareStatement(query);

            int supplierId = SupplierDaoJdbc.getSupplierIdByName(product.getSupplier().getName());
            int productCategoryId = ProductCategoryDaoJdbc.getIdByName(product.getProductCategory().getName());
            int currencyId = CurrencyDaoJdbc.getIdByName(product.getDefaultCurrency());

            statement.setString(1, product.getName());
            statement.setFloat(2, product.getDefaultPrice());
            statement.setString(3, product.getDescription());
            statement.setInt(4, supplierId);
            statement.setInt(5, productCategoryId);
            statement.setInt(6, currencyId);
            statement.setString(7, product.getImagePath());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product find(int id) {
        String query =
                "SELECT * FROM products WHERE id=?";
        Product product = null;
        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                 product = new Product(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getFloat("price"),
                        CurrencyDaoJdbc.findCurrency(result.getInt("currency_id")),
                        result.getString("description"),
                        ProductCategoryDaoJdbc.getInstance().find(result.getInt("category_id")),
                        SupplierDaoJdbc.getInstance().find(result.getInt("supplier_id")),
                        result.getString("image_path")
                );
            }
            statement.close();
            connection.close();
            result.close();
            return product;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {
        String query =
                "DELETE FROM products WHERE id=?";
        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAll() {
        String query =
                "SELECT * FROM products";
        List<Product> result = new ArrayList<>();
        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                result.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("price"),
                        CurrencyDaoJdbc.findCurrency(resultSet.getInt("currency_id")),
                        resultSet.getString("description"),
                        ProductCategoryDaoJdbc.getInstance().find(resultSet.getInt("category_id")),
                        SupplierDaoJdbc.getInstance().find(resultSet.getInt("supplier_id")),
                        resultSet.getString("image_path")
                ));
            }
            statement.close();
            connection.close();
            resultSet.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        String query =
                "SELECT * FROM products WHERE supplier_id=?";
        List<Product> result = new ArrayList<>();
        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, SupplierDaoJdbc.getSupplierIdByName(supplier.getName()));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                result.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("price"),
                        CurrencyDaoJdbc.findCurrency(resultSet.getInt("currency_id")),
                        resultSet.getString("description"),
                        ProductCategoryDaoJdbc.getInstance().find(resultSet.getInt("category_id")),
                        SupplierDaoJdbc.getInstance().find(resultSet.getInt("supplier_id")),
                        resultSet.getString("image_path")
                ));
            }
            statement.close();
            connection.close();
            resultSet.close();

        } catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        String query =
                "SELECT * FROM products WHERE category_id=?";
        List<Product> result = new ArrayList<>();
        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, ProductCategoryDaoJdbc.getIdByName(productCategory.getName()));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                result.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("price"),
                        CurrencyDaoJdbc.findCurrency(resultSet.getInt("currency_id")),
                        resultSet.getString("description"),
                        ProductCategoryDaoJdbc.getInstance().find(resultSet.getInt("category_id")),
                        SupplierDaoJdbc.getInstance().find(resultSet.getInt("supplier_id")),
                        resultSet.getString("image_path")
                ));
            }
            statement.close();
            connection.close();
            resultSet.close();

        } catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    private Connection getConnection(){
        try{
            return DbConnection.getConnection();
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("SQL Exception thrown");
        }
        return null;
    }
}
