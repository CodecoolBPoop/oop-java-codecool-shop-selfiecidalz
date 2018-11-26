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

    private static SupplierDaoJdbc instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private SupplierDaoJdbc() {
    }

    public static SupplierDaoJdbc getInstance() {
        if (instance == null) {
            instance = new SupplierDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement addSupplier = connection.prepareStatement("INSERT INTO suppliers (name, description) VALUES (?, ?);")
            ){
             addSupplier.setString(1, supplier.getName());
             addSupplier.setString(2, supplier.getDescription());
             addSupplier.execute();
            addSupplier.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Supplier find(int id) {
        String addQuery = "SELECT id, name, description FROM suppliers WHERE id=?;";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement add = connection.prepareStatement(addQuery)
        ){
            add.setInt(1, id);

            ResultSet resultSet = add.executeQuery();
            if (resultSet.next()) {
                Supplier result = new Supplier(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"));
                add.close();
                connection.close();
                resultSet.close();
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
            remove.close();
            connection.close();
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
                result.add(new Supplier(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description")));
            }
            getAllCategories.close();
            connection.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    public static int getSupplierIdByName(String name){
        String query = "SELECT id FROM suppliers WHERE name=?";
        int supplierId = -1;
        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,name);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                supplierId = result.getInt("id");
            }
            statement.close();
            connection.close();
            result.close();
            return supplierId;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }
}
