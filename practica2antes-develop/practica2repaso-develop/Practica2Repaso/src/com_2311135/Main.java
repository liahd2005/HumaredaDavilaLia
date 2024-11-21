/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com_2311135;

import com_2311135.conexion.DBConexion;
import com_2311135.controller.GastoController;
import com_2311135.repository.GastoRepository;
import com_2311135.service.GastosService;
import com_2311135.view.GastoUI;

import javax.swing.SwingUtilities;


public class Main {

    public static void main(String[] args) {
        
        
        
        String url = "jdbc:mysql://localhost:3306/gastos_personales?useSSL=false&serverTimezone=UTC";
        String user = "root"; 
        String password = "armybt21"; 

        
        GastoRepository repository = new GastoRepository(url, user, password);
        GastosService service = new GastosService(repository);
        GastoController controller = new GastoController(service);

        
        SwingUtilities.invokeLater(() -> {
            GastoUI gastoUI = new GastoUI(controller);
            gastoUI.setVisible(true);
        });

    }
    
}
