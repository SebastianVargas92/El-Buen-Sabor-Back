package com.example.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entitie.Configuracion;

@Repository("configuracionrepositorio")
public interface ConfiguracionRepository extends JpaRepository<Configuracion, Serializable> {
	
	public abstract Configuracion findById(int id);
	
	public abstract Configuracion findByEmailEmpresa(String configuracion);

}
