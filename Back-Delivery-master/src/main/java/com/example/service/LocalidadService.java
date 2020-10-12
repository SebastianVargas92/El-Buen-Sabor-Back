package com.example.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.entitie.Localidad;
import com.example.repository.LocalidadRepository;

@Service("localidadserivicio")
public class LocalidadService {

	
	@Autowired
	@Qualifier("localidadrepositorio")
	private LocalidadRepository localidadRepo;
	
	private static final Log logger = LogFactory.getLog(LocalidadService.class);

	public boolean crear(Localidad localidad) {

		logger.info("Agregando Localidad");
		try {
			localidadRepo.save(localidad);
			logger.info("localidad Guardado");
			return true;
		} catch (Exception e) {
			logger.info("Error al agregar Localidad " + e);
			return false;
		}

	}
	
	public List<Localidad> getLocalidades() {

		return localidadRepo.findAll();

	}
	
}
