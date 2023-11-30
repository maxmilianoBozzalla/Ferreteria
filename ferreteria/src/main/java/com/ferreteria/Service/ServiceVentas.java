package com.ferreteria.Service;

import com.ferreteria.Modelo.*;
import com.ferreteria.Repository.IRepositoryVentas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceVentas implements IServiceVentas {

    @Autowired
    IRepositoryVentas repositoryVentas;
    @Autowired
    IServiceProducto serviceProducto;


    @Override
    public void crearVenta(Ventas ventas) {
        //Logica para que se actualice el stock cuando generamos una venta
        try {
            for (Producto producto : ventas.getListaProductos()) {
                Producto productoActualizado = serviceProducto.traerUnProducto(producto.getCodigo_producto());
                producto.setNombre(productoActualizado.getNombre());
                producto.setMarca(productoActualizado.getMarca());
                producto.setCosto(productoActualizado.getCosto());
                if (productoActualizado.getCantidad_disponible() <= 0) { //Validacion para que no me cargue ventas si el stock esta en 0. Lanzamos una excepcion
                    throw new RuntimeException("Imposible realizar venta. Stock insuficiente");
                }

                producto.setCantidad_disponible(productoActualizado.getCantidad_disponible() - 1);
                serviceProducto.crearProducto(producto);
            }
            repositoryVentas.save(ventas);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Ventas> traerTodasVentas() {
        List<Ventas> listaVentas = repositoryVentas.findAll();

        double precioFinal = 0;
        for (int i = 0; i < listaVentas.size(); i++) {
            Ventas ventas = listaVentas.get(i);
            for (Producto producto : ventas.getListaProductos()) {
                precioFinal += producto.getCosto();
            }

            ventas.setTotal(precioFinal);
            precioFinal = 0;
        }


        return listaVentas;
    }

    @Override
    public Ventas traerUnaVenta(long codigo_venta) {
        Ventas ventas = repositoryVentas.findById(codigo_venta).orElse(null);

        double precioFinal = 0;
        for (Producto producto : ventas.getListaProductos()) {
            precioFinal += producto.getCosto();
        }

        ventas.setTotal(precioFinal);


        return ventas;

    }

    @Override
    public void eliminarVenta(long codigo_venta) {

        repositoryVentas.deleteById(codigo_venta);

    }

    @Override
    public Ventas editarVentas(long codigo_ventaNuevo, LocalDate fecha_ventaNuevo,
                               double totalNuevo, List<Producto> listaProductoNuevo,
                               Cliente clienteNuevo) {

        Ventas ventas = this.traerUnaVenta(codigo_ventaNuevo);
        ventas.setFecha_venta(fecha_ventaNuevo);
        ventas.setTotal(totalNuevo);
        ventas.setListaProductos(listaProductoNuevo);
        ventas.setUnCliente(clienteNuevo);

        this.crearVenta(ventas);

        return ventas;

    }

    @Override
    public List<Producto> listaProductosVenta(long codigo_venta) {
        Ventas venta = this.traerUnaVenta(codigo_venta);

        List<Producto> listaProductosVenta = venta.getListaProductos();

        return listaProductosVenta;
    }

    @Override
    public ResumenVentas resumenFecha(LocalDate fecha) {
        ResumenVentas resultadoVentas = new ResumenVentas();
        List<Ventas> listaVentas = this.traerTodasVentas();
        List<Ventas> listaRetronar = new ArrayList<>();

        for (Ventas ventas : listaVentas) {
            if (ventas.getFecha_venta().equals(fecha)) {
                listaRetronar.add(ventas);
            }
        }
        double total = 0.0;
        int cantidad = 0;

        for (Ventas ventas : listaRetronar) {
            total += ventas.getTotal();
        }

        resultadoVentas.setTotalVentas(listaRetronar.size());
        resultadoVentas.setPrecioTotal(total);

        return resultadoVentas;

    }

    @Override
    public Ventas mayorVenta() {
        List<Ventas> listaVentas = this.traerTodasVentas();
        Ventas ventaMayor = new Ventas();
        ventaMayor.setTotal(0.0);
        for (Ventas ventas : listaVentas) {
            if (ventas.getTotal() > ventaMayor.getTotal()) {
                ventaMayor = ventas;
            }
        }
        return ventaMayor;


    }
    //Finalizar una venta

    /* public ventasFinalizadas finalizarVentas(long codigo_venta) {

         Ventas ventas = this.traerUnaVenta(codigo_venta);
         ventasFinalizadas ventasFinalizadas = new ventasFinalizadas();

         ventasFinalizadas.setCodigo_venta(codigo_venta);
         ventasFinalizadas.setFecha_venta(ventas.getFecha_venta());
         ventasFinalizadas.setTotal(ventas.getTotal());
         ventasFinalizadas.setListaProductos(ventas.getListaProductos());
         ventasFinalizadas.setUnCliente(ventas.getUnCliente());

         for (Producto producto : ventasFinalizadas.getListaProductos()) {
             producto.setCantidad_disponible(producto.getCantidad_disponible() - 1);

             serviceProducto.crearProducto(producto);
         }

         repositoryVentasFinalzadas.save(ventasFinalizadas);

         this.eliminarVenta(codigo_venta);


         return ventasFinalizadas;

     }*/


}







