package ar.com.ada.maven.model.dao;

import ar.com.ada.maven.model.DBConnection;
import ar.com.ada.maven.model.dto.ContinentDTO;
import ar.com.ada.maven.model.dto.CountryDTO;

import java.sql.*;
import java.util.ArrayList;

public class CountryDAO implements DAO<CountryDTO> {

    private Boolean willCloseConnection = true;

    private ContinentDAO continentDAO = new ContinentDAO();

    public CountryDAO() {}

    public CountryDAO (Boolean willCloseConnection) {
        this.willCloseConnection = willCloseConnection;
    }

    @Override
    public ArrayList<CountryDTO> findAll() {
        String sql = "SELECT * FROM Country";
        ArrayList<CountryDTO> countries = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                ContinentDTO continent = continentDAO.findById(rs.getInt("id_Continent"));
                CountryDTO country = new CountryDTO(rs.getInt("id"), rs.getString("name"), rs.getInt("iso_cod"), continent);
                countries.add(country);
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return countries;
    }

    @Override
    public CountryDTO findById(Integer id) {
        String sql = "SELECT * FROM Country WHERE Id = ?";
        CountryDTO country = null;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                ContinentDTO continent = continentDAO.findById(rs.getInt("id_Continent"));
                country = new CountryDTO(rs.getInt("id"), rs.getString("name"), rs.getInt("iso_cod"), continent);
            }
            if (willCloseConnection)
                connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return country;
    }

    @Override
    public Boolean save(CountryDTO country) {
        String sql = "INSERT INTO Country (name, iso_cod, id_Continent) values (?,?,?)";
        int affectedRows = 0;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, country.getName());
            preparedStatement.setInt(2, country.getIsoCode());
            preparedStatement.setInt(3, country.getContinent().getId());
            affectedRows = preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return affectedRows == 1;
    }

    @Override
    public Boolean update(CountryDTO country, Integer id) {
        String sql = "UPDATE Country SET name = ?, iso_cod = ?, id_Continent = ? WHERE Id = ?";
        int hasUpdate = 0;

        CountryDTO countryDB = findById(id);

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, country.getName());
            preparedStatement.setInt(2, country.getIsoCode());
            preparedStatement.setInt(3, country.getContinent().getId());
            preparedStatement.setInt(4, country.getId());

            if (!(country.getName().equals(countryDB.getName()) &&
                    country.getIsoCode().equals(countryDB.getIsoCode()) &&
                    country.getContinent().equals(countryDB.getContinent())))
                hasUpdate = preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR ON COUNTRY update: " + e.getMessage());

        }
        return hasUpdate == 1;
    }

    @Override
    public Boolean delete(Integer id) {
        String sql = "DELETE FROM Country WHERE Id = ?";
        int hasDelete = 0;

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            hasDelete = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR ON COUNTRY delete: " + e.getMessage());
        }
        return hasDelete ==1;
    }
}
