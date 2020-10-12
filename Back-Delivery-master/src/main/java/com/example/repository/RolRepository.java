package com.example.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entitie.Rol;

@Repository("rolrepositorio")
public interface RolRepository extends JpaRepository<Rol, Serializable>{
	
	public abstract Rol findById(int id);

}
