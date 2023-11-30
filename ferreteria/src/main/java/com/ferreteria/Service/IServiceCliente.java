package com.ferreteria.Service;

import com.ferreteria.Modelo.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IServiceCliente {

    public void crearCLiente(Cliente cliente);
    public List<Cliente> todosCleintes();

    public Cliente traerUnCliente(long id_cliente);

    public void eliminarCliente(long id_cliente);

    public Cliente editarCliente(long id_cliente,String nombreNuevo,
                                 String apellidoNuevo,
                                 String dniNuevo);
}
