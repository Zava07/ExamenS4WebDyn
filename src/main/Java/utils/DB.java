package main.Java.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
    public static Connection connect() throws SQLException {
        String URL = "jdbc:mysql://172.80.237.53:3306/db_s2_ETU003777?useSSL=false&serverTimezone=UTC";
        Properties properties = new Properties();
        properties.setProperty("user", "ETU003777");
        properties.setProperty("password", "tIcJsd8X");

        Connection connection = null;

        try {
            // Chargement explicite du driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, properties);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC non trouvé !");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erreur de connexion : " + e.getMessage());
            throw e;
        }

        return connection;
    }
}
