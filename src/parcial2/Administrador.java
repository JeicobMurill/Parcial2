/**
 *
 * @author Jeicob Murillo
 */


package parcial2;


public class Administrador {
    
    // Credenciales predefinidas de un usuario administrador.
    private final String nombreUsuario = "admin";
    private final String contrasena = "1234";

    
    public boolean autenticar(String usuario, String contrasena) {
        return this.nombreUsuario.equals(usuario) && this.contrasena.equals(contrasena);
    }
}