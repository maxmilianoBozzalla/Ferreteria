package com.ferreteria.Controller;

import com.ferreteria.DTO.mayorVentaDto;
import com.ferreteria.Modelo.Ventas;
import com.ferreteria.Service.IServiceVentas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DtoController {

    @Autowired
    IServiceVentas serviceVentas;

    @GetMapping("/ventas/mayor_venta")
    public mayorVentaDto mayorVenta() {
        mayorVentaDto mayorVenta = new mayorVentaDto();
        Ventas ventas = serviceVentas.mayorVenta();
        mayorVenta.setCodigo_venta(ventas.getCodigo_venta());
        mayorVenta.setListProductos(ventas.getListaProductos());
        mayorVenta.setNombre(ventas.getUnCliente().getNombre());
        mayorVenta.setApellido(ventas.getUnCliente().getApellido());
        mayorVenta.setTotal(ventas.getTotal());


        return mayorVenta;
    }

}
