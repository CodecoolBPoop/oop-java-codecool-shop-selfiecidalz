package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrencyDaoJdbc {

    private static CurrencyDaoJdbc instance = null;

    private CurrencyDaoJdbc() {
    }

    public static CurrencyDaoJdbc getInstance() {
        if (instance == null) {
            instance = new CurrencyDaoJdbc();
        }
        return instance;
    }


    public void add(String name){
        try {
            String query =
                    "INSERT INTO currencies(name) VALUES(?)";
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setString(1, name);
            statement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static String findCurrency(int id) {
        String query = "SELECT name FROM currencies WHERE id=?;";
        String currencyId = "";
        try {
            Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                currencyId = resultSet.getString("name");
            }
            return currencyId;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getIdByName(String name){
        String query =
                "SELECT id FROM currencies WHERE name=?;";
        int currencyId = -1;
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                 currencyId = resultSet.getInt("id");
            }
            return currencyId;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    private  static Connection getConnection(){
        try{
            return DbConnection.getConnection();
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("SQL Exception thrown");
        }
        return null;
    }
}
