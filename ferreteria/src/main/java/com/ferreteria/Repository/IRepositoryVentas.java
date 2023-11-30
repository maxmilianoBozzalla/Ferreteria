package com.ferreteria.Repository;


import com.ferreteria.Modelo.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryVentas extends JpaRepository<Ventas,Long> {
}
