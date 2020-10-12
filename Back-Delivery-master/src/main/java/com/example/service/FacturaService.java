package com.example.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.entitie.Factura;
import com.example.repository.FacturaRepository;


@Service("facturaservicio")
public class FacturaService {
	
	@Autowired
	@Qualifier("facturarepositorio")
	private FacturaRepository facturaRepo;
	
	private static final Log logger = LogFactory.getLog(ClienteService.class);

	public boolean crear(Factura factura) {

		logger.info("Agregando Factura");
		try {
			facturaRepo.save(factura);
			logger.info("Factura Guardado");
			return true;
		} catch (Exception e) {
			logger.info("Error al agregar Factura " + e);
			return false;
		}
	}
	

	public List<Factura> getFacturas() {
		return facturaRepo.findAll();
	}
	
	public Page<Factura> paginas(Pageable pageable) {
		return facturaRepo.findAll(pageable);
	}

}
