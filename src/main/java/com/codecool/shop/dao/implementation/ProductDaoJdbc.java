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
                "INSERT INTO products(name, price, description, supplier_id, category_id, currency_id) VALUES (?,?,?,?,?,?)";
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
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product find(int id) {
        String query =
                "SELECT * FROM products WHERE id=?";
        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();
            Product product = new Product(
                    result.getString("name"),
                    result.getFloat("price"),
                    CurrencyDaoJdbc.findCurrency(result.getInt("currency_id")),
                    result.getString("description"),
                    ProductCategoryDaoJdbc.getInstance().find(result.getInt("category_id")),
                    SupplierDaoJdbc.getInstance().find(result.getInt("supplier_id"))
            );
            return product;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
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
