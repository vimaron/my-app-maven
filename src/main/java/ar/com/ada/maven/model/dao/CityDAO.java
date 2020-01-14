package ar.com.ada.maven.model.dao;

import ar.com.ada.maven.model.DBConnection;
import ar.com.ada.maven.model.dto.CityDTO;
import ar.com.ada.maven.model.dto.CountryDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CityDAO implements DAO<CityDTO> {

    private Boolean willCloseConnection = true;
    private CountryDAO countryDAO = new CountryDAO();

    public CityDAO() {
    }

    public CityDAO(Boolean willCloseConnection) {
        this.willCloseConnection = willCloseConnection;
    }

    @Override
    public ArrayList<CityDTO> findAll() {
        String sql = "SELECT * FROM City";
        ArrayList<CityDTO> cities = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                CountryDTO country = countryDAO.findById(rs.getInt("Country_id"));
                CityDTO city = new CityDTO(rs.getInt("id"), rs.getString("name"), country);
                cities.add(city);
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR CITY FindAll: " + e.getMessage());
        }
        return cities;
    }

    @Override
    public CityDTO findById(Integer id) {
        String sql = "SELECT * FROM City WHERE Id = ?";
        CityDTO city = null;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                CountryDTO country = countryDAO.findById(rs.getInt("Country_id"));
                city = new  CityDTO(rs.getInt("id"), rs.getString("name"), country);
            }
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR CITY FindById: " + e.getMessage());
        }
        return city;
    }

    @Override
    public Boolean save(CityDTO city) {
        String sql = "INSERT INTO City (name, Country_id) VALUES (?,?)";
        int affectedRows = 0;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, city.getName());
            preparedStatement.setInt(2, city.getCountryID().getId());
            affectedRows = preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR CITY save: " + e.getMessage());
        }
        return affectedRows == 1;
    }

    @Override
    public Boolean update(CityDTO city, Integer id) {
        String sql = "UPDATE City SET name = ?, Country_id = ? WHERE Id = ?";
        int hasUpdate = 0;

        CityDTO cityDB = findById(id);

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, city.getName());
            preparedStatement.setInt(2, city.getCountryID().getId());
            preparedStatement.setInt(3, id);

            if (!(city.getName().equals(cityDB.getName()) && city.getCountryID().equals(cityDB.getCountryID())));
            hasUpdate = preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR CITY update: " + e.getMessage());
        }
        return hasUpdate == 1;
    }

    @Override
    public Boolean delete(Integer id) {
        String sql = "DELETE FROM City WHERE Id = ?";
        int hasDelete = 0;

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            hasDelete = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR CITY delete: " + e.getMessage());
        }
        return hasDelete == 1;
    }
}