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
        String user = "root"; // Cambia según tu configuración
        String password = "armybt21"; // Cambia según tu configuración

        // Configurar los datos de conexión a la base de datos
        
        

        // Crear las instancias de las capas del sistema
        GastoRepository repository = new GastoRepository(url, user, password);
        GastosService service = new GastosService(repository);
        GastoController controller = new GastoController(service);

        // Lanzar la interfaz gráfica
        SwingUtilities.invokeLater(() -> {
            GastoUI gastoUI = new GastoUI(controller);
            gastoUI.setVisible(true);
        });

    }
    
}
/*String url="";
        String user="";
        String password= "";
        
        BookRepository repository= new BookRepository(url, user, password);
        
        Book book1= new Book.BookBuilder();
                .setTitle("1984")
                .setAuthor("George Orwell")        
                .setGenre("Dystopian")
                        .setYear(1949)
                        .build();
        
        Book book2= new Book.BookBuilder()           
                .setTitle("To Kill a Mockingbird")
                .setAuthor("Harper Lee")
                .setGenre("Fiction")
                .setYear(1949)
                .build();
        
        repository.save(book1);
        repository.save(book2);
        
        System.out.println("Books by author 'George Orwell': ");
        List<Book> booksByAuthor =repository.findByAuthor("George Orwell");
        booksByAuthor.forEach(System.out::println);
        
        System.out.println("Books with title containing '1984': ");
        List<Book> booksByTitle =repository.findByTitle("1984");
        booksByTitle.forEach(System.out::println);
        */