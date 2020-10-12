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

import com.example.entitie.RubroArticulo;
import com.example.repository.RubroArticuloRepository;

@Service("rubroarticuloservicio")
public class RubroArticuloService {

	@Autowired
	@Qualifier("rubroarticulorepositorio")
	private RubroArticuloRepository rubroArticuloRepositorio;

	private static final Log logger = LogFactory.getLog(ClienteService.class);

	public boolean crear(RubroArticulo rubro) {

		logger.info("Agregando Rubro");
		try {
			rubroArticuloRepositorio.save(rubro);
			logger.info("Rubro Articulo Guardado");
			return true;
		} catch (Exception e) {
			logger.info("Error al agregar Rubro Articulo " + e);
			return false;
		}

	}

	public List<RubroArticulo> getRubrosArticulo() {
		return rubroArticuloRepositorio.findAll();
	}

	public Page<RubroArticulo> paginas(Pageable pageable) {

		return rubroArticuloRepositorio.findByFechaBaja(null, pageable);
	}

	public RubroArticulo getRubro(int id) {
		return rubroArticuloRepositorio.findById(id);
	}

	public List<RubroArticulo> getRubrosArticuloR(int id) {
		RubroArticulo r = rubroArticuloRepositorio.findById(id);

		return rubroArticuloRepositorio.findByRubro(r);

	}

	public List<RubroArticulo> getRubrosArticuloRRR(int id) {

		List<RubroArticulo> rubros = new ArrayList<RubroArticulo>();
		metodoRecursivo(rubros, id);
		return rubros;
	}

	public void metodoRecursivo(List<RubroArticulo> rubro, int id) {

		RubroArticulo r = rubroArticuloRepositorio.findById(id);
		rubro.add(r);
		List<RubroArticulo> hijos = rubroArticuloRepositorio.findByRubro(r);
		if (hijos != null) {
			for (RubroArticulo ra : hijos) {
				metodoRecursivo(rubro, ra.getId());
			}

		}

	}
}
