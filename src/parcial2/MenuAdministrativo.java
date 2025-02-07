/**
 *
 * @author Jeicob Murillo
 */



package parcial2;


import javax.swing.*;
import java.util.*;

public class MenuAdministrativo {

    // Método que muestra el menú con opciones disponibles para el usuario.
    public static void mostrarMenu() {
      
        String[] opciones = {"1. Listar productos", "2. Agregar producto", "3. Modificar producto", "4. Salir"};
        
     
        String opcion = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:", 
                                                             "Menú Administrativo", 
                                                             JOptionPane.PLAIN_MESSAGE, 
                                                             null, 
                                                             opciones, 
                                                             opciones[0]);

      
        if (opcion == null) {
            JOptionPane.showMessageDialog(null, "Operación cancelada.");
            System.exit(0); 
            return; 
        }

      
        switch (opcion) {
            case "1. Listar productos" -> listarProductos();  
            case "2. Agregar producto" -> agregarProducto();  
            case "3. Modificar producto" -> modificarProducto();  
            case "4. Salir" -> System.exit(0);  
            default -> JOptionPane.showMessageDialog(null, "Opción no válida.");
}
    }

    
    private static void listarProductos() {
        List<Producto> productos = DatabaseText.obtenerProductos(); 
        StringBuilder sb = new StringBuilder();  
        
      
        for (Producto producto : productos) {
            sb.append(producto.mostrarInfo()).append("\n"); 
        }
        
        // Muestra la lista de productos en un cuadro de mensaje.
        JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Productos", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método que permite agregar un nuevo producto.
    private static void agregarProducto() {
       
        String nombre = JOptionPane.showInputDialog("Nombre del producto:");
        double precio = Double.parseDouble(JOptionPane.showInputDialog("Precio del producto:"));
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad del producto:"));

       
        Producto nuevoProducto = new Producto(nombre, precio, cantidad);
        
      
        DatabaseText.agregarProducto(nuevoProducto);
        
      
        JOptionPane.showMessageDialog(null, "Producto agregado exitosamente.");
    }

    // Método que permite modificar un producto existente.
    private static void modificarProducto() {
        List<Producto> productos = DatabaseText.obtenerProductos();  
        
        // Si no hay productos en la lista, muestra un mensaje y termina el método.
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos en la bodega.");
            return;
        }

       
        String[] nombresProductos = new String[productos.size()];
        for (int i = 0; i < productos.size(); i++) {
            nombresProductos[i] = productos.get(i).getNombre();
        }

       
        String productoSeleccionado = (String) JOptionPane.showInputDialog(
                null, 
                "Seleccione un producto para modificar:", 
                "Modificar producto", 
                JOptionPane.QUESTION_MESSAGE, 
                null, 
                nombresProductos, 
                nombresProductos[0]);

       
        if (productoSeleccionado == null) {
            JOptionPane.showMessageDialog(null, "Operación cancelada.");
            return;
        }

     
        Producto productoAModificar = null;
        for (Producto producto : productos) {
            if (producto.getNombre().equals(productoSeleccionado)) {
                productoAModificar = producto;
                break;
            }
        }

       
        if (productoAModificar == null) {
            JOptionPane.showMessageDialog(null, "Producto no encontrado.");
            return;
        }

      
        JOptionPane.showMessageDialog(null, "Producto seleccionado: " + productoAModificar.getNombre() + 
                                      "\nPrecio: " + productoAModificar.getPrecio() + 
                                      "\nCantidad: " + productoAModificar.getCantidad());

        // Pide al usuario un nuevo nombre para el producto, si lo desea.
        String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre del producto (dejar vacío para no modificar):");
        if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
            productoAModificar.setNombre(nuevoNombre);
        }

        // Pide al usuario un nuevo precio para el producto, si lo desea.
        double nuevoPrecio = productoAModificar.getPrecio();
        String precioStr = JOptionPane.showInputDialog("Nuevo precio del producto (dejar vacío para no modificar):");
        if (precioStr != null && !precioStr.trim().isEmpty()) {
            try {
                nuevoPrecio = Double.parseDouble(precioStr);  // Intenta convertir el precio ingresado a un número
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "El precio debe ser un número válido.");
                return;
            }
        }
        productoAModificar.setPrecio(nuevoPrecio);

        // Pide al usuario una nueva cantidad para el producto, si lo desea.
        int nuevaCantidad = productoAModificar.getCantidad();
        String cantidadStr = JOptionPane.showInputDialog("Nueva cantidad del producto (dejar vacío para no modificar):");
        if (cantidadStr != null && !cantidadStr.trim().isEmpty()) {
            try {
                nuevaCantidad = Integer.parseInt(cantidadStr);  // Intenta convertir la cantidad ingresada a un número
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "La cantidad debe ser un número válido.");
                return;
            }
        }
        productoAModificar.setCantidad(nuevaCantidad);

        // Actualiza la lista de productos en el archivo.
        DatabaseText.actualizarProducto(productos);

    
        JOptionPane.showMessageDialog(null, "Producto modificado exitosamente.");
    }
}