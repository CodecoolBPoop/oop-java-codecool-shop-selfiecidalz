package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrencyDaoJdbc {


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

    public static int getIdByName(String name){
        try {
            String query =
                    "SELECT id FROM currencies WHERE name=?;";
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.getInt("id");
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
