package com.example.repository;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.entitie.ArticuloManufacturado;
import com.example.entitie.RubroGeneral;

@Repository("manufacturadorepositorio")
public interface ManufacturadoRepository extends JpaRepository<ArticuloManufacturado, Serializable>,
		PagingAndSortingRepository<ArticuloManufacturado, Serializable> {
	public abstract Page<ArticuloManufacturado> findAll(Pageable pageable);

	public abstract Page<ArticuloManufacturado> findByFechaBaja(Date fecha, Pageable pageable);
	
	public abstract Page<ArticuloManufacturado> findByFechaBajaAndDenominacionContaining(Date fecha, String buscado, Pageable pageable);
	
	public abstract Page<ArticuloManufacturado> findByFechaBajaAndRubro(Date fecha,RubroGeneral rubro, Pageable pageable);
	
	public abstract Page<ArticuloManufacturado> findByFechaBajaAndRubroAndDenominacionContaining(Date fecha,RubroGeneral rubro, String buscado, Pageable pageable);
	
	public abstract ArticuloManufacturado findByDenominacion(String denominacion);

}
