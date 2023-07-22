package org.example.peakSoft.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Util {
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String username = "postgres";
    private static final String password = "postgres";

    public static Connection getconnection() {
        Connection connection = null;
        try {
            connection= DriverManager.getConnection(url,username,password);
            System.out.println("Connected to dataBase...");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }return connection;
    }
}
