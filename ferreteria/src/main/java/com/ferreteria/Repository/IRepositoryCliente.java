package com.ferreteria.Repository;

import com.ferreteria.Modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryCliente extends JpaRepository<Cliente,Long> {
}
