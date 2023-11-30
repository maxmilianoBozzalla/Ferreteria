package com.ferreteria.Service;

import com.ferreteria.Modelo.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface IServiceVentas {

    public void crearVenta(Ventas ventas);

    public List<Ventas> traerTodasVentas();

    public Ventas traerUnaVenta(long codigo_venta);

    public void eliminarVenta(long codigo_venta);

    public Ventas editarVentas(long codigo_ventaNuevo, LocalDate fecha_ventaNuevo,
                               double totalNuevo, List<Producto> listaProductoNuevo,
                               Cliente clienteNuevo);

    public List<Producto> listaProductosVenta(long codigo_venta);

    public ResumenVentas resumenFecha(LocalDate fecha);

    public Ventas mayorVenta();

}
