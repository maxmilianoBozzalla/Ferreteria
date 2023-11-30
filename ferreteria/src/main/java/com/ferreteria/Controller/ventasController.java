package com.ferreteria.Controller;

import com.ferreteria.Modelo.Cliente;
import com.ferreteria.Modelo.Producto;
import com.ferreteria.Modelo.Ventas;
import com.ferreteria.Service.IServiceVentas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ventasController {

    @Autowired
    IServiceVentas serviceVentas;

    @PostMapping("/ventas/crear")
    public void crearVenta(@RequestBody Ventas ventas) {

        serviceVentas.crearVenta(ventas);

    }

    @GetMapping("/ventas")
    public List<Ventas> traerTodos() {
        List<Ventas> lista = serviceVentas.traerTodasVentas();

        return lista;
    }

    @GetMapping("/ventas/{codigo_ventas}")
    public Ventas traerUnProducto(@PathVariable long codigo_ventas) {

        Ventas ventas = serviceVentas.traerUnaVenta(codigo_ventas);

        return ventas;
    }

    @DeleteMapping("/ventas/eliminar/{codigo_venta}")
    public void eliminarProducto(@PathVariable long codigo_venta) {
        serviceVentas.eliminarVenta(codigo_venta);
    }

    @PostMapping("/ventas/editar/{codigo_ventas}")
    public Ventas editarProducto(@PathVariable long codigo_ventas,
                                 @RequestParam(required = false, name = "fecha") LocalDate fechaNuevo,
                                 @RequestParam(required = false, name = "total") Double totalNuevo,
                                 @RequestParam(required = false, name = "listaProductos") List<Producto> listaProductosNuevo,
                                 @RequestParam(required = false, name = "cliente") Cliente clienteNuevo) {

        Ventas ventas = serviceVentas.traerUnaVenta(codigo_ventas);
        if (fechaNuevo != null)
            ventas.setFecha_venta(fechaNuevo);

        if (totalNuevo != null)
            ventas.setTotal(totalNuevo);

        if (listaProductosNuevo != null)
            ventas.setListaProductos(listaProductosNuevo);

        if (clienteNuevo != null)
            ventas.setUnCliente(clienteNuevo);

        serviceVentas.crearVenta(ventas);
        return ventas;

    }




}
