package com.codecool.shop.dao;
import java.sql.*;

public class DbConnection {

    private static String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "paradicsom3";

    public static void setTestingDatabase() {

        DATABASE = "jdbc:postgresql://localhost:5432/codecoolshopTest";
    }

    public static java.sql.Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

}
