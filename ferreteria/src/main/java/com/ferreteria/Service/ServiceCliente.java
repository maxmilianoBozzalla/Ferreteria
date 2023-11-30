package com.ferreteria.Service;

import com.ferreteria.Modelo.Cliente;
import com.ferreteria.Repository.IRepositoryCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCliente implements IServiceCliente {

    @Autowired
    IRepositoryCliente repositoryCliente;


    @Override
    public void crearCLiente(Cliente cliente) {
        repositoryCliente.save(cliente);
    }

    @Override
    public List<Cliente> todosCleintes() {
        List<Cliente> listaCliente = repositoryCliente.findAll();

        return listaCliente;
    }

    @Override
    public Cliente traerUnCliente(long id_cliente) {
        Cliente cliente = repositoryCliente.findById(id_cliente).orElse(null);

        return cliente;
    }

    @Override
    public void eliminarCliente(long id_cliente) {
        repositoryCliente.deleteById(id_cliente);
    }

    @Override
    public Cliente editarCliente(long id_cliente, String nombreNuevo,
                                 String apellidoNuevo,
                                 String dniNuevo) {
        Cliente cliente = this.traerUnCliente(id_cliente);

        cliente.setNombre(nombreNuevo);
        cliente.setApellido(apellidoNuevo);
        cliente.setDni(dniNuevo);

        this.crearCLiente(cliente);

        return cliente;
    }
}
