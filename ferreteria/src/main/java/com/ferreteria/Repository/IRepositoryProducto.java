package com.ferreteria.Repository;

import com.ferreteria.Modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryProducto extends JpaRepository<Producto, Long> {
}
