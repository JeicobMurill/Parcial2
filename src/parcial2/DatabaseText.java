/**
 *
 * @author Jeicob Murillo
 */


package parcial2;

import java.io.*;
import javax.swing.*;
import java.util.*;

public class DatabaseText {
    
    // Definición de la constante FILE_NAME que guarda el nombre del archivo de texto donde se almacenan los productos.
    private static final String FILE_NAME = "productos.txt";

    public static void verificarArchivo() {
        File archivo = new File(FILE_NAME);  // Crea un objeto File que representa el archivo "productos.txt".
        
        // Si el archivo no existe, lo crea.
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();  
            } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void agregarProducto(Producto producto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
           
            String linea = producto.getNombre() + "," + producto.getPrecio() + "," + producto.getCantidad();
            
           
            writer.write(linea);
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    // Método para obtener todos los productos almacenados en el archivo "productos.txt".
    public static List<Producto> obtenerProductos() {
        List<Producto> productos = new ArrayList<>();  // Lista para almacenar los productos leídos.
        
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linea;
            
            // Lee cada línea del archivo hasta que no haya más líneas.
            while ((linea = reader.readLine()) != null) {
                // Divide la línea en partes usando la coma como separador.
                String[] datos = linea.split(",");
                String nombre = datos[0];  // El primer valor es el nombre del producto.
                double precio = Double.parseDouble(datos[1]);  // El segundo valor es el precio.
                int cantidad = Integer.parseInt(datos[2]);  // El tercer valor es la cantidad.

              
                productos.add(new Producto(nombre, precio, cantidad));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return productos;
    }

    // Método para actualizar los productos en el archivo "productos.txt".
    public static void actualizarProducto(List<Producto> productos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
           
            for (Producto producto : productos) {
               
                String linea = producto.getNombre() + "," + producto.getPrecio() + "," + producto.getCantidad();
                
             
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}