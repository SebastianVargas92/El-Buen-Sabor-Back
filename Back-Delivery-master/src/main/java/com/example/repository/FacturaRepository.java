package com.example.repository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.entitie.Factura;

@Repository("facturarepositorio")
public interface FacturaRepository extends JpaRepository<Factura, Serializable>,
PagingAndSortingRepository<Factura, Serializable> {
	
	public abstract Page<Factura> findAll(Pageable pageable);

}
