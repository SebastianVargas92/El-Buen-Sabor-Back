package com.example.repository;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entitie.RubroArticulo;

@Repository("rubroarticulorepositorio")
public interface RubroArticuloRepository extends JpaRepository<RubroArticulo, Serializable>{
	
	public abstract Page<RubroArticulo> findAll(Pageable pageable);
	public abstract Page<RubroArticulo> findByFechaBaja(Date fecha, Pageable pageable);
	
	public abstract RubroArticulo findById(int id);
	
	public abstract List<RubroArticulo> findByRubro(RubroArticulo rubro);

}
