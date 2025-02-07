/**
 *
 * @author Jeicob Murillo
 */


package parcial2;


import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        
        // Verifica que el archivo "productos.txt" exista. Si no, lo crea.
        DatabaseText.verificarArchivo();

        // Solicita al usuario que ingrese su nombre de usuario y contraseña.
        String usuario = JOptionPane.showInputDialog("Ingrese el nombre de usuario:");
        String contrasena = JOptionPane.showInputDialog("Ingrese la contraseña:");

        // Crea una instancia de la clase Administrador para realizar la autenticación.
        Administrador admin = new Administrador();

        // Llama al método autenticar() para validar las credenciales del usuario.
        if (admin.autenticar(usuario, contrasena)) {
            // Si la autenticación es exitosa, muestra un mensaje de éxito y entra en el bucle del menú administrativo.
            JOptionPane.showMessageDialog(null, "Autenticación exitosa.");
            while (true) {
                MenuAdministrativo.mostrarMenu();
            }
        } else {
            // Si la autenticación falla, muestra un mensaje de error y termina la ejecución.
            JOptionPane.showMessageDialog(null, "Autenticación fallida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}