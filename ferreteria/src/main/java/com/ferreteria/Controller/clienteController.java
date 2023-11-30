package com.ferreteria.Controller;

import com.ferreteria.Modelo.Cliente;
import com.ferreteria.Modelo.Producto;
import com.ferreteria.Service.IServiceCliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class clienteController {

    @Autowired
    IServiceCliente serviceCliente;

    @PostMapping(value = "/clientes/crear" , consumes = "application/json")
    public void crearCliente(@RequestBody Cliente cliente) {
        serviceCliente.crearCLiente(cliente);
    }

    @GetMapping("/clientes")
    public List<Cliente> traerTodos() {
        List<Cliente> listaClientes = serviceCliente.todosCleintes();

        return listaClientes;
    }

    @GetMapping("/clientes/{id_cliente}")
    public Cliente traerUnCliente(@PathVariable long id_cliente) {
        Cliente cliente = serviceCliente.traerUnCliente(id_cliente);

        return cliente;
    }

    @DeleteMapping("/clientes/eliminar/{id_cliente}")
    public void eliminarCliente(@PathVariable long id_cliente) {
        serviceCliente.eliminarCliente(id_cliente);
    }

    @PostMapping("/cliente/editar/{id_cliente}")
    public Cliente editarCLiente(@PathVariable long id_cliente,
                                 @RequestParam(required = false, name = "nombre") String nombreNuevo,
                                 @RequestParam(required = false, name = "apellido") String apellidoNuevo,
                                 @RequestParam(required = false, name = "dni") String dniNuevo) {

        Cliente cliente = serviceCliente.traerUnCliente(id_cliente);
        if(nombreNuevo != null){
            cliente.setNombre(nombreNuevo);
        }
        if(apellidoNuevo != null){
            cliente.setApellido(apellidoNuevo);
        }
        if(dniNuevo != null){
            cliente.setDni(dniNuevo);
        }

        serviceCliente.crearCLiente(cliente);

        return cliente;

    }



}

















