/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com_2311135.service;

import com_2311135.model.Gastos;
import com_2311135.repository.GastoRepository;
import java.util.List;
import java.util.stream.Collectors;

public class GastosService {
    private final GastoRepository gastoRepository;

    public GastosService(GastoRepository gastoRepository) {
        this.gastoRepository = gastoRepository;
    }

    
    private boolean isValidCategory(String categoria) {
        return categoria.equalsIgnoreCase("Alimentación") ||
               categoria.equalsIgnoreCase("Transporte") ||
               categoria.equalsIgnoreCase("Entretenimiento") ||
               categoria.equalsIgnoreCase("Salud") ||
               categoria.equalsIgnoreCase("Otros");
    }

    
    private boolean isBelowMonthlyLimit(double totalActual, double nuevoMonto) {
        return (totalActual + nuevoMonto) <= 5000;
    }

    
    public void saveGasto(Gastos gastos) {
        if (gastos.getMonto() <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que 0.");
        }

        if (!isValidCategory(gastos.getCategoria())) {
            throw new IllegalArgumentException("Categoría no válida.");
        }

        // Validar límite mensual
        double totalActual = gastoRepository.findAll().stream()
                .mapToDouble(Gastos::getMonto)
                .sum();

        if (!isBelowMonthlyLimit(totalActual, gastos.getMonto())) {
            throw new IllegalArgumentException("El gasto supera el límite mensual de S/5000.");
        }

        gastoRepository.save(gastos);
    }

    
    public void updateGasto(Gastos gastos) {
        if (gastos.getMonto() <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que 0.");
        }

        if (!isValidCategory(gastos.getCategoria())) {
            throw new IllegalArgumentException("Categoría no válida.");
        }

        gastoRepository.update(gastos);
    }

    
    public void deleteGasto(int id) {
        gastoRepository.delete(id);
    }

    
    public List<Gastos> getAllGastos() {
        return gastoRepository.findAll();
    }

    
    public String getResumenGastos() {
        List<Gastos> gastos = gastoRepository.findAll();
        double total = gastos.stream().mapToDouble(Gastos::getMonto).sum();

        StringBuilder resumen = new StringBuilder("Resumen de Gastos:\n");
        resumen.append("Total: S/").append(total).append("\n");

        gastos.stream()
                .map(Gastos::getCategoria)
                .distinct()
                .forEach(categoria -> {
                    long cantidad = gastos.stream()
                            .filter(g -> g.getCategoria().equalsIgnoreCase(categoria))
                            .count();
                    resumen.append(categoria).append(": ").append(cantidad).append(" gastos\n");
                });

        return resumen.toString();
    }
    public List<Gastos> getGastosByCategoria(String categoria) {
    return gastoRepository.findAll().stream()
            .filter(g -> g.getCategoria().equalsIgnoreCase(categoria))
            .collect(Collectors.toList());
}
}
