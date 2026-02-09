/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionpedidos.logica;

import gestionpedidos.dto.Pedido;
import java.sql.*;
import java.util.ArrayList;
import org.openide.util.Exceptions;

/**
 *
 * @author Robert
 */
public class PedidoDAO {

    private Connection conexion;
    private final String USUARIO = "root";
    private final String PASSWORD = "root";
    private final String MAQUINA = "localhost";
    private final String BD = "gestion_pedidos";

    public PedidoDAO() {
        conexion = conectar();
    }

    private Connection conectar() {
        Connection con = null;
        String url = "jdbc:mysql://" + MAQUINA + "/" + BD;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, USUARIO, PASSWORD);
        } catch (SQLException e) {
            System.err.println("ERROR AL CARGAR LOS PEDIDOS DE LA BD.");
        } catch (ClassNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }
        return con;
    }

    /**
     * Crea el pedido
     *
     * @param pedido
     * @return true si ha podido crearlo, false si no
     */
    public boolean create(Pedido pedido) {
        if (pedido == null) {
            return false;
        }
        String sql = "INSERT INTO PEDIDOS(producto, categoria, precio_unitario, cantidad,fecha_pedido) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, pedido.getProducto());
            sentencia.setString(2, pedido.getCategoria());
            sentencia.setDouble(3, pedido.getPrecioUnitario());
            sentencia.setInt(4, pedido.getCantidad());

            // Convertimos java.util.Date a java.sql.Date usando milisegundos
            long ms = pedido.getFechaPedido().getTime();
            java.sql.Date fechaSQL = new java.sql.Date(ms);
            sentencia.setDate(5, fechaSQL);

            // executeUpdate() es lo que realmente inserta los datos en la BD
            int filasAfectadas = sentencia.executeUpdate();

            // Si se insertó al menos una fila, devolvemos true
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear pedido: " + e.getMessage());
            return false;
        }
    }

    /**
     * Recoge la lista de pedidos
     *
     * @return listaPedidos
     */
    public ArrayList<Pedido> listaPedidos() {
        ArrayList<Pedido> listaPedidos = new ArrayList<>();
        Pedido pedido = null;
        String sql = "SELECT * FROM PEDIDOS";
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet rs = sentencia.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id_pedido");
                String producto = rs.getString("producto");
                String categoria = rs.getString("categoria");
                Double precioUnitario = rs.getDouble("precio_unitario");
                int cantidad = rs.getInt("cantidad");
                Date fechaPedido = rs.getDate("fecha_pedido");

                pedido = new Pedido(id, producto, categoria, precioUnitario, cantidad, fechaPedido);
                listaPedidos.add(pedido);
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar pedidos: " + e.getMessage());
        }
        return listaPedidos;
    }

    /**
     * Recoge la lista por categoría concreta
     * @param categoria
     * @return 
     */
    public ArrayList<Pedido> listaPedidosPorCategoria(String categoria) {
        ArrayList<Pedido> listaPedidos = new ArrayList<>();
        Pedido pedido = null;
        String sql = "SELECT * FROM PEDIDOS WHERE CATEGORIA = ?";
        try {
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, categoria);
            ResultSet rs = sentencia.executeQuery();
             while (rs.next()) {
                int id = rs.getInt("id_pedido");
                String producto = rs.getString("producto");
                Double precioUnitario = rs.getDouble("precio_unitario");
                int cantidad = rs.getInt("cantidad");
                Date fechaPedido = rs.getDate("fecha_pedido");

                pedido = new Pedido(id, producto, categoria, precioUnitario, cantidad, fechaPedido);
                listaPedidos.add(pedido);
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar pedidos por categoría: " + e.getMessage());
        }

        return listaPedidos;
    }

    /**
     * Finaliza la conexión con la BD
     */
    public void cerrarConexion() {
        try {
            conexion.close();
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

}
