package com.ferreteria.Controller;

import com.ferreteria.Modelo.Producto;
import com.ferreteria.Service.IServiceProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class productoController {

    @Autowired
    IServiceProducto serviceProducto;

    @PostMapping(value = "/productos/crear", consumes = "application/json")
    public void crearProducto(@RequestBody Producto producto) {
        serviceProducto.crearProducto(producto);
    }

    @GetMapping("/productos")
    public List<Producto> traerTodos() {
        List<Producto> lista = serviceProducto.todosProductos();

        return lista;
    }

    @GetMapping("/productos/{id_producto}")
    public Producto traerUnProducto(@PathVariable long id_producto) {

        Producto producto = serviceProducto.traerUnProducto(id_producto);

        return producto;
    }

    @DeleteMapping("/productos/eliminar/{id_producto}")
    public void eliminarProducto(@PathVariable long id_producto) {
        serviceProducto.eliminarProducto(id_producto);
    }

    @PostMapping("/productos/editar/{codigo_producto}")
    public Producto editarProducto(@PathVariable long codigo_producto,
                                   @RequestParam(required = false, name = "nombre") String nombreNuevo,
                                   @RequestParam(required = false, name = "marca") String marcaNuevo,
                                   @RequestParam(required = false, name = "costo") Double costoNuevo,
                                   @RequestParam(required = false, name = "cantidad") Integer cantidad_disponibleNuevo) {

        Producto producto = serviceProducto.traerUnProducto(codigo_producto);
        if (nombreNuevo != null)
            producto.setNombre(nombreNuevo);

        if (marcaNuevo != null)
            producto.setMarca(marcaNuevo);

        if (costoNuevo != null)
            producto.setCosto(costoNuevo);

        if (cantidad_disponibleNuevo != null)
            producto.setCantidad_disponible(cantidad_disponibleNuevo);

        serviceProducto.crearProducto(producto);
        return producto;

    }


    @GetMapping("/productos/falta_stock")
    public List<Producto> stockMinimo() {
        List<Producto> listaRetornar = serviceProducto.stockMinimo();

        return listaRetornar;
    }
}
