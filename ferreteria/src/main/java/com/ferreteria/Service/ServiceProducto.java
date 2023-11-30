package com.ferreteria.Service;

import com.ferreteria.Modelo.Producto;
import com.ferreteria.Repository.IRepositoryProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceProducto implements IServiceProducto {
    @Autowired
    IRepositoryProducto repositoryProducto;

    @Override
    public void crearProducto(Producto producto) {
        repositoryProducto.save(producto);
    }

    @Override
    public List<Producto> todosProductos() {

       List<Producto> listaProducto = repositoryProducto.findAll();

       return listaProducto;
    }

    @Override
    public Producto traerUnProducto(long id_producto) {

        Producto producto = repositoryProducto.findById(id_producto).orElse(null);

        return producto;

    }

    @Override
    public void eliminarProducto(long id_producto) {
        repositoryProducto.deleteById(id_producto);
    }

    @Override
    public Producto editarProducto(long id_producto, String nombreNuevo,
                                   String marcaNuevo, Double costoNuevo,
                                   int cantidad_disponibleNuevo) {

        Producto producto = this.traerUnProducto(id_producto);

        producto.setNombre(nombreNuevo);
        producto.setMarca(marcaNuevo);
        producto.setCosto(costoNuevo);
        producto.setCantidad_disponible(cantidad_disponibleNuevo);

        this.crearProducto(producto);

        return producto;

    }

    @Override
    public List<Producto> stockMinimo() {
        List<Producto> listaProducto = repositoryProducto.findAll();
        List<Producto> listaProducMinimo = new ArrayList<>();

        for(Producto producto : listaProducto){
            if(producto.getCantidad_disponible() < 5){
                listaProducMinimo.add(producto);
            }
        }
        return listaProducMinimo;
    }

}


















