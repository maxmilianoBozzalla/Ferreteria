package com.ferreteria.Service;

import com.ferreteria.Modelo.Producto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IServiceProducto {
    public void crearProducto(Producto producto);
    public List<Producto> todosProductos();

    public Producto traerUnProducto(long id_producto);

    public void eliminarProducto(long id_producto);

    public Producto editarProducto(long id_producto,String nombreNuevo,
                                    String marcaNuevo,
                                    Double costoNuevo,
                                    int cantidad_disponibleNuevo);

    public List<Producto> stockMinimo();




}
