package ar.com.ada.maven.model.dao;

import ar.com.ada.maven.model.DBConnection;
import ar.com.ada.maven.model.dto.ContinentDTO;

import javax.swing.*;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContinentDAO implements DAO<ContinentDTO> {


    @Override
    public List<ContinentDTO> findAll(){
        String sql= "SELECT * FROM Continent";
        List<ContinentDTO> continents = new ArrayList<>();

        try {
            Connection connection = DBConnection.getConnection();
            Statement statement= connection.createStatement();
            ResultSet rs= statement.executeQuery(sql);
            while (rs.next()){
                ContinentDTO continent = new ContinentDTO(rs.getInt("id"), rs.getString("name"));
                continents.add(continent);
            }
            connection.close();
        }catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e){
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return continents;
    }

    @Override
    public ContinentDTO findById(Integer id){
        String sql = "SELECT * FROM Continent WHERE id = ?";
        ContinentDTO continent = null;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                continent = new ContinentDTO(rs.getInt("id"), rs.getString("name"));
            connection.close();
        } catch (Exception e){
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return continent;
    }
    @Override
    public Boolean save(ContinentDTO continent) {
        String sql = "INSERT INTO Continent (name) VALUES (?)";
        int hasInsert = 0;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, continent.getName());
            hasInsert = preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return hasInsert == 1;
    }

    @Override
    public Boolean update(ContinentDTO continent, Integer id) {
        String sql = "UPDATE Continent SET name = ? WHERE id = ?";
        int hasUpdate = 0;
        ContinentDTO continentDB = findById(id);
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, continent.getName());
            preparedStatement.setInt(2, id);


            if (!continent.getName().equals(continent.getName()))
                hasUpdate = preparedStatement.executeUpdate();
         connection.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONECTION ERROR: " + e.getMessage());
        }
        // si es =1 entonces retorna true
        return hasUpdate == 1;
    }

    @Override
    public Boolean delete(Integer id) {
        String sql = "DELETE FROM Continent WHERE id = ?";
        ContinentDTO continent = null;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(2, continent.getName());
            preparedStatement.executeQuery(sql);
         connection.close();
        } catch (InstantiationException | SQLException | IllegalAccessException | ClassNotFoundException e) {
            System.out.println("CONECTION ERROR: " + e.getMessage());
        }
        return null;
    }

    public ContinentDTO findByName(String findName){
        String sql = "SELECT * FROM Continent WHERE name = ?";
        ContinentDTO findN = null;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, findName);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                findN = new ContinentDTO(rs.getInt("id"), rs.getString("name"));
            connection.close();
        } catch (Exception e){
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return findN;
    }

    public List<ContinentDTO> findAll(int limit, int offset){
        String sql = "SELECT * FROM Continent LIMIT ? OFFSET ?";
        List<ContinentDTO> continents = new ArrayList<>();

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                ContinentDTO continent = new ContinentDTO(rs.getInt("id"), rs.getString("name"));
                continents.add(continent);
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e){
            System.out.println("ERROR DE CONEXION: " + e.getMessage());
        }
        return continents;
    }

    public int getTotalContinents(){
        String sql = "SELECT COUNT(*) AS total FROM Continent;";
        int total = 0;
        try {
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) total = rs.getInt("total");
            connection.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e){
            System.out.println("ERROR DE CONEXION AL OBTENER EL TOTAL DE CONTINENTES: " + e.getMessage());
        }
        return total;
    }
}
