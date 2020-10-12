package com.example.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.entitie.Cliente;
import com.example.entitie.Pedido;

@Repository("pedidorepositorio")
public interface PedidoRepository extends JpaRepository<Pedido, Serializable>,
PagingAndSortingRepository<Pedido, Serializable> {
	
	public abstract Page<Pedido> findByEstado(int estado,Pageable pageable);
	
	public abstract List<Pedido> findByEstado(int estado);
	
	public abstract Page<Pedido> findByCliente(Cliente cliente,Pageable pageable);
	
	public abstract Page<Pedido> findByClienteAndEstado(Cliente cliente,int estado,Pageable pageable);
	
	public abstract Pedido findByNumero(long numero);
	
	public abstract List<Pedido> findByFechaBetween(Date des,Date has);
	
	public abstract List<Pedido> findByFechaBetweenAndEstado(Date des,Date has,int estado);
	
	public abstract List<Pedido> findByFechaBetweenAndCliente(Date des,Date has,Cliente cliente);


}

