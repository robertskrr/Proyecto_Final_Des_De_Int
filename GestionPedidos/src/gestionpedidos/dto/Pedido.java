/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionpedidos.dto;

import java.util.*;

/**
 *
 * @author Robert
 */
public class Pedido {

    private int id;
    private String producto;
    private String categoria;
    private double precioUnitario;
    private int cantidad;
    private Date fechaPedido;

    /**
     * Constructor para crear el pedido en la BD
     *
     * @param producto
     * @param categoria
     * @param precioUnitario
     * @param cantidad
     * @param fechaPedido
     */
    public Pedido(String producto, String categoria, double precioUnitario, int cantidad, Date fechaPedido) {
        this.producto = producto;
        this.categoria = categoria;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.fechaPedido = fechaPedido;
    }

    /**
     * Constructor para recoger todos los datos de la BD
     *
     * @param id
     * @param producto
     * @param categoria
     * @param precioUnitario
     * @param cantidad
     * @param fechaPedido
     */
    public Pedido(int id, String producto, String categoria, double precioUnitario, int cantidad, Date fechaPedido) {
        this.id = id;
        this.producto = producto;
        this.categoria = categoria;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.fechaPedido = fechaPedido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
    
    

}
