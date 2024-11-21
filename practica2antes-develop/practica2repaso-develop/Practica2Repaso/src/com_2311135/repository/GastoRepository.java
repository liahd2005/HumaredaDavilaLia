/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com_2311135.repository;

import com_2311135.model.Gastos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class GastoRepository 

 {
    private final String url;
    private final String user;
    private final String password;

    public GastoRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void save(Gastos gastos) {
        String sql = "INSERT INTO gastos (descripcion, categoria, monto, fecha_gasto) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, gastos.getDescripcion());
            statement.setString(2, gastos.getCategoria());
            statement.setDouble(3, gastos.getMonto());
            statement.setString(4, gastos.getFechaGasto());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el gasto", e);
        }
    }

    public List<Gastos> findAll() {
        List<Gastos> gastos = new ArrayList<>();
        String sql = "SELECT * FROM gastos";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                gastos.add(new Gastos.Builder()
                        .setId(resultSet.getInt("id"))
                        .setDescripcion(resultSet.getString("descripcion"))
                        .setCategoria(resultSet.getString("categoria"))
                        .setMonto(resultSet.getDouble("monto"))
                        .setFechaGasto(resultSet.getString("fecha_gasto"))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar los gastos", e);
        }
        return gastos;
    }

    public void update(Gastos gastos) {
        String sql = "UPDATE gastos SET descripcion = ?, categoria = ?, monto = ?, fecha_gasto = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, gastos.getDescripcion());
            statement.setString(2, gastos.getCategoria());
            statement.setDouble(3, gastos.getMonto());
            statement.setString(4, gastos.getFechaGasto());
            statement.setInt(5, gastos.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el gasto", e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM gastos WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el gasto", e);
        }
    }
}



