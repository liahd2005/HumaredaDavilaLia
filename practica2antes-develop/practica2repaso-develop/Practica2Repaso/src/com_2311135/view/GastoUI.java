/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com_2311135.view;

import com_2311135.controller.GastoController;
import com_2311135.model.Gastos;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GastoUI extends JFrame {
    private final GastoController controller;
    private final DefaultTableModel tableModel;
    private final JTable table;
    private final JComboBox<String> categoriaFilterCombo;

    public GastoUI(GastoController controller) {
        this.controller = controller;

        setTitle("Gestión de Gastos Personales");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Descripción", "Categoría", "Monto", "Fecha"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Agregar Gasto");
        JButton editButton = new JButton("Editar Gasto");
        JButton deleteButton = new JButton("Eliminar Gasto");
        JButton summaryButton = new JButton("Resumen");

        
        categoriaFilterCombo = new JComboBox<>(new String[]{"Todos", "Alimentación", "Transporte", "Entretenimiento", "Salud", "Otros"});
        categoriaFilterCombo.addActionListener(e -> loadGastos());  // Actualizar la tabla al cambiar la categoría

        buttonPanel.add(categoriaFilterCombo);
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(summaryButton);

        add(buttonPanel, BorderLayout.SOUTH);

        
        loadGastos();

        
        addButton.addActionListener(e -> {
            new GastosForm(this, "Registrar Gasto", controller, null).setVisible(true);
            loadGastos(); 
        });

        
        editButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow(); 
            if (selectedRow >= 0) {
                int id = (int) tableModel.getValueAt(selectedRow, 0); 
                Gastos gastoToEdit = controller.getAllGastos().stream()
                        .filter(g -> g.getId() == id)
                        .findFirst()
                        .orElse(null);

                if (gastoToEdit != null) {
                    new GastosForm(this, "Editar Gasto", controller, gastoToEdit).setVisible(true);
                    loadGastos(); 
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona un gasto para editar.");
            }
        });

        
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow(); 
            if (selectedRow >= 0) {
                int id = (int) tableModel.getValueAt(selectedRow, 0); 

                int confirm = JOptionPane.showConfirmDialog(this,
                        "¿Está seguro de que desea eliminar este gasto?",
                        "Confirmar Eliminación",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    controller.deleteGasto(id); 
                    loadGastos(); 
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona un gasto para eliminar.");
            }
        });

        
        summaryButton.addActionListener(e -> {
            String resumen = controller.getResumenGastos();
            JOptionPane.showMessageDialog(this, resumen);
        });
    }

    
    private void loadGastos() {
        String categoriaFiltro = (String) categoriaFilterCombo.getSelectedItem();
        List<Gastos> gastos;
        
        if ("Todos".equals(categoriaFiltro)) {
            gastos = controller.getAllGastos(); 
        } else {
            gastos = controller.getGastosByCategoria(categoriaFiltro); 
        }

        tableModel.setRowCount(0); 
        for (Gastos gasto : gastos) {
            tableModel.addRow(new Object[]{
                    gasto.getId(),
                    gasto.getDescripcion(),
                    gasto.getCategoria(),
                    gasto.getMonto(),
                    gasto.getFechaGasto()
            });
        }
    }
}
        

