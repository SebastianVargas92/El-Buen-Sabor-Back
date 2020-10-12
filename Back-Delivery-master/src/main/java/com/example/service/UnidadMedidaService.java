package com.example.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.entitie.UnidadMedida;
import com.example.repository.UnidadMedidaRepository;

@Service("unidadmedidaservicio")
public class UnidadMedidaService {
	
	@Autowired
	@Qualifier("unidadmedidarepositorio")
	private UnidadMedidaRepository medidaRepo;
	
	
	private static final Log logger = LogFactory.getLog(LocalidadService.class);

	public boolean crear(UnidadMedida medida) {

		logger.info("Agregando Unidad medida");
		try {
			medidaRepo.save(medida);
			logger.info("Unidad medida Guardado");
			return true;
		} catch (Exception e) {
			logger.info("Error al agregar Unidad Medida " + e);
			return false;
		}

	}
	
	public List<UnidadMedida> getMedidas() {

		return medidaRepo.findAll();

	}
	
	public UnidadMedida getMedida(int id) {
		return medidaRepo.getOne(id);
	}
	
	
	

}
