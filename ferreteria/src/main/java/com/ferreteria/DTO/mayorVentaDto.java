package com.ferreteria.DTO;

import com.ferreteria.Modelo.Producto;

import java.util.List;

public class mayorVentaDto {
    long codigo_venta;
    double total;
    List<Producto> listProductos;
    String nombre;
    String apellido;

    public mayorVentaDto() {
    }

    public mayorVentaDto(long codigo_venta, double total, List<Producto> listProductos, String nombre, String apellido) {
        this.codigo_venta = codigo_venta;
        this.total = total;
        this.listProductos = listProductos;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public long getCodigo_venta() {
        return codigo_venta;
    }

    public void setCodigo_venta(long codigo_venta) {
        this.codigo_venta = codigo_venta;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Producto> getListProductos() {
        return listProductos;
    }

    public void setListProductos(List<Producto> listProductos) {
        this.listProductos = listProductos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}


//Obtener el codigo_venta, el total, la cantidad de productos, el nombre del cliente y el
//apellido del cliente de la venta con el monto más alto de todas.