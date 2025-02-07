/**
 *
 * @author Jeicob Murillo
 */


package parcial2;


public class Producto {
    
    // Definición de las variables de instancia de la clase Producto.
    private String nombre;  // Variable para almacenar el nombre del producto
    private double precio;  // Variable para almacenar el precio del producto
    private int cantidad;   // Variable para almacenar la cantidad disponible del producto


    public Producto(String nombre, double precio, int cantidad) {
        this.nombre = nombre;     
        this.precio = precio;    
        this.cantidad = cantidad;  
    }


    public String getNombre() {
        return nombre;
    }

    // Este método "setter" establece un nuevo nombre para el producto.
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Este método "getter" devuelve el precio del producto.
    public double getPrecio() {
        return precio;
    }

    // Este método "setter" establece un nuevo precio para el producto.
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Este método "getter" devuelve la cantidad disponible del producto.
    public int getCantidad() {
        return cantidad;
    }

    // Este método "setter" establece una nueva cantidad para el producto.
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String mostrarInfo() {
        return "Nombre: " + nombre + ", Precio: " + precio + ", Cantidad: " + cantidad;
    }
}