package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.entitie.ArticuloInsumo;
import com.example.repository.InsumoRepository;

@Service("insumoservicio")
public class InsumoService {

	@Autowired
	@Qualifier("insumorepositorio")
	private InsumoRepository insumoRepositorio;

	private static final Log logger = LogFactory.getLog(ClienteService.class);

	public boolean crear(ArticuloInsumo insumo) {

		logger.info("Agregando Insumo");
		try {
			insumoRepositorio.save(insumo);
			logger.info("Insumo Guardado");
			return true;
		} catch (Exception e) {
			logger.info("Error al agregar Insumo " + e);
			return false;
		}

	}

	public List<ArticuloInsumo> getInsumos() {

		return insumoRepositorio.findAll();

	}

	public List<ArticuloInsumo> getInsumosF() {

		return insumoRepositorio.findByFechaBaja(null);

	}

	public Page<ArticuloInsumo> paginas(Pageable pageable) {

		return insumoRepositorio.findByFechaBaja(null, pageable);
	}

	public Page<ArticuloInsumo> paginasB(String buscado, Pageable pageable) {
		if (buscado == null) {
			return insumoRepositorio.findByFechaBaja(null, pageable);
		} else {
			return insumoRepositorio.findByFechaBajaAndDenominacionContaining(null, buscado, pageable);
		}
	}

	public List<ArticuloInsumo> insumoSinStock() {
		List<ArticuloInsumo> sinStock = new ArrayList<ArticuloInsumo>();
		for (ArticuloInsumo ai : this.getInsumosF()) {
			if (ai.getStockActual() <= ai.getStockMinimo()) {
				sinStock.add(ai);
			}
		}
		return sinStock;
	}

}
