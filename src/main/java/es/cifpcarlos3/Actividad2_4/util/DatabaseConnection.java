package es.cifpcarlos3.Actividad2_4.util;

import java.sql.*;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mariadb://localhost:3306/banco";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public Connection getConn() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
