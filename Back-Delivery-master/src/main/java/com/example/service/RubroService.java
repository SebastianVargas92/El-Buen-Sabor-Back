package com.example.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.entitie.RubroGeneral;
import com.example.repository.RubroRepository;

@Service("rubroservicio")
public class RubroService {

	@Autowired
	@Qualifier("rubrorepositorio")
	private RubroRepository rubroRepositorio;

	private static final Log logger = LogFactory.getLog(ClienteService.class);

	public boolean crear(RubroGeneral rubro) {

		logger.info("Agregando Rubro General");
		try {
			rubroRepositorio.save(rubro);
			logger.info("Rubro Guardado");
			return true;
		} catch (Exception e) {
			logger.info("Error al agregar Rubro");
			return false;
		}

	}

	public List<RubroGeneral> getRubros() {

		return rubroRepositorio.findAll();

	}
	
	public RubroGeneral getRubro(int id) {
	//	return rubroRepositorio.findByDenominacion(denominacion);
		return rubroRepositorio.findById(id);
	}

	public Page<RubroGeneral> paginas(Pageable pageable) {

		return rubroRepositorio.findByFechaBaja(null, pageable);
	}

}
