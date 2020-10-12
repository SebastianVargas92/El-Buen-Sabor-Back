package com.example.repository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.entitie.DetallePedido;

@Repository("detallePedidoRepositorio")
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Serializable>,
PagingAndSortingRepository<DetallePedido, Serializable> {
public abstract Page<DetallePedido> findAll(Pageable pageable);

}
