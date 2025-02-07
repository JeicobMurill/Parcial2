/**
 *
 * @author Jeicob Murillo
 */


package parcial2;


public class Administrador {
    
    // Credenciales predefinidas de un usuario administrador.
    private String nombreUsuario = "admin";
    private String contrasena = "1234";

    
    public boolean autenticar(String usuario, String contrasena) {
        return this.nombreUsuario.equals(usuario) && this.contrasena.equals(contrasena);
    }
}