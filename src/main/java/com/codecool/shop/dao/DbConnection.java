package com.codecool.shop.dao;
import java.sql.*;

public class DbConnection {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/ccshop";
    private static final String DB_USER = "ccshop";
    private static final String DB_PASSWORD = "qwe123";

    private java.sql.Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

}
