package com.example.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.entitie.Configuracion;
import com.example.repository.ConfiguracionRepository;

@Service("configuracionservicio")
public class ConfiguracionService {
	
	@Autowired
	@Qualifier("configuracionrepositorio")
	private ConfiguracionRepository repo;
	
	private static final Log logger = LogFactory.getLog(ConfiguracionService.class);

	public boolean crear(Configuracion configuracion) {

		logger.info("Agregando Configuracion");
		try {
			repo.save(configuracion);
			logger.info("Configuracion Guardado");
			return true;
		} catch (Exception e) {
			logger.info("Error al agregar Configuracion " + e);
			return false;
		}

	}
	
	public List<Configuracion> getConfiguraciones() {

		return repo.findAll();

	}
	
	public Configuracion getConfiguracion(int id){
		return repo.findById(id);
	}
	
	public Configuracion getConfiguracionEmail(String correo){
		return repo.findByEmailEmpresa(correo);
	}

}
