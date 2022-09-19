package com.asstmngm.assetmanagement;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public Connection databaseLink;

    public Connection getConnection() {
        String databaseUser = "root";
        String databasePassword = "root";
        String url = "jdbc:mysql://localhost:3306/asset_management";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
            System.out.println("Connection Failed!");
        }

        return databaseLink;
    }
}
