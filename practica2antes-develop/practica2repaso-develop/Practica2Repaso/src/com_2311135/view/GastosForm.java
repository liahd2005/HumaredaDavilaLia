/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com_2311135.view;

import com_2311135.controller.GastoController;
import com_2311135.model.Gastos;


import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class GastosForm extends JDialog{
    private JTextField descripcionField;
    private JComboBox<String> categoriaCombo;
    private JTextField montoField;
    private JTextField fechaField;
    private JButton saveButton;

    public GastosForm(JFrame parent, String title, GastoController controller, Gastos gastoToEdit) {
        super(parent, title, true);
        setSize(400, 300);
        setLayout(new GridLayout(5, 2));

        
        add(new JLabel("Descripción:"));
        descripcionField = new JTextField();
        add(descripcionField);

        add(new JLabel("Categoría:"));
        
        categoriaCombo = new JComboBox<>(new String[]{"Alimentación", "Transporte", "Entretenimiento", "Salud", "Otros"});
        add(categoriaCombo);

        add(new JLabel("Monto:"));
        montoField = new JTextField();
        add(montoField);

        add(new JLabel("Fecha (YYYY-MM-DD):"));
        fechaField = new JTextField(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        add(fechaField);

        
        saveButton = new JButton("Guardar");
        add(saveButton);

        
        if (gastoToEdit != null) {
            descripcionField.setText(gastoToEdit.getDescripcion());
            categoriaCombo.setSelectedItem(gastoToEdit.getCategoria());
            montoField.setText(String.valueOf(gastoToEdit.getMonto()));
            fechaField.setText(gastoToEdit.getFechaGasto());
        }

        saveButton.addActionListener(e -> {
            try {
                String descripcion = descripcionField.getText();
                String categoria = categoriaCombo.getSelectedItem().toString(); 
                double monto = Double.parseDouble(montoField.getText());
                String fecha = fechaField.getText();

                if (gastoToEdit == null) {
                    
                    controller.addGasto(descripcion, categoria, monto, fecha);
                } else {
                    
                    controller.editGasto(gastoToEdit.getId(), descripcion, categoria, monto, fecha);
                }
                dispose(); 
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El monto debe ser un número válido.");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });
    }
}