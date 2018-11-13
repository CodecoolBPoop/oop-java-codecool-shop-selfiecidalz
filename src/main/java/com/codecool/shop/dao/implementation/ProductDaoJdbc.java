package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DbConnection;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {

    @Override
    public void add(Product product){
        try {
            String query =
                "INSERT INTO products(name, price, description, supplier_id, category_id) VALUES (?,?,?,?,?)";
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setString(1, product.getName());
            statement.setFloat(2, product.getDefaultPrice());
            statement.setString(3, product.getDescription());
//            int supplierId =
            int productCategoryId = ProductCategoryDaoJdbc.getIdByName(product.getProductCategory().getName());
            statement.setInt(4, productCategoryId);
            int currencyId = CurrencyDaoJdbc.getIdByName(product.getDefaultCurrency());
            statement.setInt(5, currencyId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product find(int id) {
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
