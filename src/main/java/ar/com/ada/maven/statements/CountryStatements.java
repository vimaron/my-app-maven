package ar.com.ada.maven.statements;

import ar.com.ada.maven.model.ConnectionDB;

import java.sql.*;

public class CountryStatements {
    public static void listCountries() throws ClassNotFoundException,
            SQLException, InstantiationException, IllegalAccessException {
        String sql = "SELECT * FROM City";
        Connection conn = ConnectionDB.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            System.out.println(rs.getInt("id"));
            System.out.println(rs.getString("name"));
        }
        conn.close();
    }
    public static void insertCountry() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Integer continentId = 2;
        String country = "Chile";
        String sql = "INSERT INTO Country (name, Continent_id) VALUES (?, ?)";
        Connection conn = ConnectionDB.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, country);
        pst.setInt(2, continentId);

        int insert = pst.executeUpdate();
        System.out.println(insert);
        conn.close();
    }
}
