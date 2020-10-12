package com.example.repository;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entitie.RubroGeneral;

@Repository("rubrorepositorio")
public interface RubroRepository extends JpaRepository<RubroGeneral, Serializable> {
	
	public abstract Page<RubroGeneral> findByFechaBaja(Date fecha, Pageable pageable);
	
	public abstract RubroGeneral findByDenominacion(String denominacion);
	
	public abstract RubroGeneral findById(int id);

}
