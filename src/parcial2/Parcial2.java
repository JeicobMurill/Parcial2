
package parcial2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Parcial2 {

    private static final String ARCHIVO_USUARIOS = "users.txt";
    private static final String ARCHIVO_PRODUCTOS = "productos.txt";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                crearYMostrarInterfaz();
            }
        });
    }

    private static void crearYMostrarInterfaz() {
        JFrame frame = new JFrame("Implementos Deportivos S.A");
        frame.setDefaultCloseOperation(3);
        frame.setSize(400, 300);

        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JLabel usuarioLabel = new JLabel("Usuario:");
        JTextField usuarioField = new JTextField();
        JLabel contraseñaLabel = new JLabel("Contraseña:");
        JPasswordField contraseñaField = new JPasswordField();

        loginPanel.add(usuarioLabel);
        loginPanel.add(usuarioField);
        loginPanel.add(contraseñaLabel);
        loginPanel.add(contraseñaField);

        JButton loginButton = new JButton("Ingresar");
        loginPanel.add(loginButton);

        JPanel optionsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JButton agregarUsuarioButton = new JButton("Agregar Usuario");
        JButton buscarProductoButton = new JButton("Buscar Producto");
        JButton agregarProductoButton = new JButton("Agregar Producto");
        JButton salirButton = new JButton("Salir");

        optionsPanel.add(agregarUsuarioButton);
        optionsPanel.add(buscarProductoButton);
        optionsPanel.add(agregarProductoButton);
        optionsPanel.add(salirButton);

        frame.add(loginPanel);
        frame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreUsuario = usuarioField.getText();
                char[] contraseñaChars = contraseñaField.getPassword();
                String contraseña = new String(contraseñaChars);

                if (verificarLogin(nombreUsuario, contraseña)) {
                    JOptionPane.showMessageDialog(null, "Bienvenido, " + nombreUsuario + "!");
                    frame.getContentPane().removeAll();
                    frame.add(optionsPanel);
                    frame.revalidate();
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Acceso Denegado");
                }
            }
        });

        agregarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nuevoUsuario = JOptionPane.showInputDialog("Ingrese el nombre de usuario:");
                String nuevaContraseña = JOptionPane.showInputDialog("Ingrese la contraseña:");
                if (nuevoUsuario != null && nuevaContraseña != null) {
                    agregarUsuario(nuevoUsuario, nuevaContraseña);
                }
            }
        });

        buscarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreProducto = JOptionPane.showInputDialog("Ingrese el nombre del producto:");
                if (nombreProducto != null) {
                    buscarProducto(nombreProducto);
                }
            }
        });

        agregarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreProducto = JOptionPane.showInputDialog("Ingrese el nombre del producto:");
                if (nombreProducto != null && !nombreProducto.trim().isEmpty()) {
                    String cantidad = JOptionPane.showInputDialog("Ingrese la cantidad del producto:");
                    if (cantidad != null) {
                        agregarProducto(nombreProducto, cantidad);
                    }
                }
            }
        });

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.add(loginPanel);
                frame.revalidate();
                frame.repaint();
                usuarioField.setText("");
                contraseñaField.setText("");
            }
        });
    }

    private static void agregarUsuario(String nombreUsuario, String contraseña) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO_USUARIOS, true))) {
            writer.println(nombreUsuario + "," + contraseña);
            JOptionPane.showMessageDialog(null, "Usuario agregado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean verificarLogin(String nombreUsuario, String contraseña) {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_USUARIOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                String usuarioGuardado = partes[0];
                String contraseñaGuardada = partes[1];
                if (usuarioGuardado.equals(nombreUsuario) && contraseñaGuardada.equals(contraseña)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static void buscarProducto(String nombreProducto) {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_PRODUCTOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                String nombre = partes[0];
                String cantidad = partes[1];
                if (nombre.equalsIgnoreCase(nombreProducto)) {
                    JOptionPane.showMessageDialog(null, "Producto: " + nombre + "\nCantidad: " + cantidad);
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Producto no encontrado.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void agregarProducto(String nombreProducto, String cantidad) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO_PRODUCTOS, true))) {
            writer.println(nombreProducto + "," + cantidad);
            JOptionPane.showMessageDialog(null, "Producto agregado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}