package ar.com.ada.maven.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static Connection connection;
    private static String user= "root";
    private static String host = "jdbc:mysql://localhost:3306/";
    private static String password = "Vi123cky";
    private static String db = "PracticaAda2";
    private static String drive = "com.mysql.cj.jdbc.Driver";


    private ConnectionDB(){}

    public static Connection getConnection() throws SQLException,
            ClassNotFoundException,
            IllegalAccessException,
            InstantiationException {
        if (connection == null || connection.isClosed()){
            Class.forName(drive).newInstance();
            connection = DriverManager.getConnection(host+db, user, password);
        }
        return connection;
    }
}
