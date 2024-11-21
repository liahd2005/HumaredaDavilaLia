/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com_2311135.controller;

import com_2311135.model.Gastos;
import com_2311135.service.GastosService;

import java.util.List;

public class GastoController {
    private final GastosService gastoService;

    public GastoController(GastosService gastoService) {
        this.gastoService = gastoService;
    }

    public void addGasto(String descripcion, String categoria, double monto, String fechaGasto) {
        Gastos gastos = new Gastos.Builder()
                .setDescripcion(descripcion)
                .setCategoria(categoria)
                .setMonto(monto)
                .setFechaGasto(fechaGasto)
                .build();
        gastoService.saveGasto(gastos);
    }

    public void editGasto(int id, String descripcion, String categoria, double monto, String fechaGasto) {
        Gastos gastos = new Gastos.Builder()
                .setId(id)
                .setDescripcion(descripcion)
                .setCategoria(categoria)
                .setMonto(monto)
                .setFechaGasto(fechaGasto)
                .build();
        gastoService.updateGasto(gastos);
    }

    public void deleteGasto(int id) {
        gastoService.deleteGasto(id);
    }

    public List<Gastos> getAllGastos() {
        return gastoService.getAllGastos();
    }

    // Obtener los gastos filtrados por categoría
    public List<Gastos> getGastosByCategoria(String categoria) {
        return gastoService.getGastosByCategoria(categoria);
    }

    public String getResumenGastos() {
        return gastoService.getResumenGastos();
    }
}