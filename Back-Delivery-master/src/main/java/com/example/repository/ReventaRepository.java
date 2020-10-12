package com.example.repository;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.entitie.ArticuloReventa;
import com.example.entitie.RubroArticulo;

@Repository("reventarepositorio")
public interface ReventaRepository extends JpaRepository<ArticuloReventa, Serializable> , PagingAndSortingRepository<ArticuloReventa, Serializable>{

	public abstract Page<ArticuloReventa> findAll(Pageable pageable);
	public abstract Page<ArticuloReventa> findByFechaBaja(Date fecha, Pageable pageable);
	public abstract Page<ArticuloReventa> findByFechaBajaAndDenominacionContaining(Date fecha, String buscado, Pageable pageable);
	public abstract List<ArticuloReventa> findByFechaBaja(Date fecha);
	public abstract Page<ArticuloReventa> findByFechaBajaAndRubroArticulo(Date fecha,RubroArticulo rubro, Pageable pageable);
	public abstract List<ArticuloReventa> findByFechaBajaAndRubroArticulo(Date fecha,RubroArticulo rubro);
	
}
