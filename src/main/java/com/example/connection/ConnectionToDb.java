package com.example.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionToDb {
        public static Connection connect() {
            Connection c = null;
            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/postgres",
                                "postgres", "emiw2003");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            return c;
        }



}

