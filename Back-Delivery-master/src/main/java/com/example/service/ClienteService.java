package com.example.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.entitie.Cliente;
import com.example.entitie.Rol;
import com.example.repository.ClienteRepository;
import com.example.repository.RolRepository;

@Service("servicio")
public class ClienteService {

	@Autowired
	@Qualifier("repositorio")
	private ClienteRepository repository;
	
	@Autowired
	@Qualifier("rolrepositorio")
	private RolRepository rolRepo;
	
	private static final Log logger = LogFactory.getLog(ClienteService.class);

	public boolean crear(Cliente cliente) {

		logger.info("Agregando Cliente");
		try {
			repository.save(cliente);
			logger.info("Cliente Guardado");
			return true;
		} catch (Exception e) {
			logger.info("Error al agregar Cliente " + e);
			return false;
		}

	}
	
	public boolean actualizar(Cliente cliente) {
		logger.info("Actualizando Cliente");
		try {
			
			repository.save(cliente);
			logger.info("Cliente Actualizado");
			return true;
		} catch (Exception e) {
			logger.info("Error en actualizar Cliente " + e);
			return false;
		}

	}
	
	public List<Cliente> getClientes() {

		return repository.findAll();

	}
	
	public List<Cliente> getClientesRol(Rol rol) {

		return repository.findByRol(rol);

	}
	
	public Cliente getCliente(String correo) {
		try {
			
			return repository.findByCorreo(correo);
			
		} catch (Exception e) {
			return null;
		}
		
	}

	public boolean registrar(Cliente cliente) {
		try {
			
			Cliente c = this.getCliente(cliente.getCorreo());
			if(c== null) {
				this.crear(cliente);
			}
				return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public Page<Cliente> paginas(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	public Page<Cliente> paginasR(int rol, Pageable pageable) {
		if(rol == 0) {
			return repository.findAll(pageable);
			
		}else {
		Rol r = rolRepo.findById(rol);
		return repository.findByRol(r, pageable);
	}}
	
}
