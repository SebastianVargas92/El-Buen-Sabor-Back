package com.example.repository;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.entitie.ArticuloInsumo;

@Repository("insumorepositorio")
public interface InsumoRepository extends JpaRepository<ArticuloInsumo, Serializable> , PagingAndSortingRepository<ArticuloInsumo, Serializable>{

	public abstract Page<ArticuloInsumo> findAll(Pageable pageable);
	public abstract Page<ArticuloInsumo> findByFechaBaja(Date fecha, Pageable pageable);
	public abstract Page<ArticuloInsumo> findByFechaBajaAndDenominacionContaining(Date fecha, String buscado, Pageable pageable);
	public abstract List<ArticuloInsumo> findByFechaBaja(Date fecha);
	
}
