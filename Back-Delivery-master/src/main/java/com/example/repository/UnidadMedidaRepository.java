package com.example.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entitie.UnidadMedida;

@Repository("unidadmedidarepositorio")
public interface UnidadMedidaRepository extends JpaRepository<UnidadMedida, Serializable>{
	
	public abstract UnidadMedida findById(int id);

}
