package com.example.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entitie.Cliente;
import com.example.entitie.Rol;

@Repository("repositorio")
public interface ClienteRepository extends JpaRepository<Cliente, Serializable>{
	
	public abstract Cliente findByCorreo(String correo);
	
	public abstract List<Cliente> findByRol(Rol rol);
	
	//@Query(value = "SELECT * FROM cliente WHERE correo = ?1", nativeQuery = true)
	 // Cliente findByCorre(String correo);
	public abstract Page<Cliente> findAll(Pageable pageable);
	
	public abstract Page<Cliente> findByRol(Rol rol, Pageable pageable);
	
}