package com.example.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.entitie.ArticuloReventa;
import com.example.entitie.RubroArticulo;
import com.example.entitie.UnidadMedida;
import com.example.repository.ReventaRepository;
import com.example.repository.RubroArticuloRepository;
import com.example.repository.UnidadMedidaRepository;

@Service("reventaservicio")
public class ReventaService {

	@Autowired
	@Qualifier("reventarepositorio")
	private ReventaRepository reventaRepositorio;

	@Autowired
	@Qualifier("rubroarticulorepositorio")
	private RubroArticuloRepository rubroRepositorio;
	
	@Autowired
	@Qualifier("unidadmedidarepositorio")
	private UnidadMedidaRepository medidaRepo;

	private static final Log logger = LogFactory.getLog(ClienteService.class);

	public boolean actualizar(ArticuloReventa reventa) {
		logger.info("Actualizando Articulo Reventa");
		try {
			reventaRepositorio.save(reventa);
			logger.info("Reventa Actualizado");
			return true;
		} catch (Exception e) {
			logger.info("Error al actualizar reventa " + e);
			return false;
		}
	}

	public List<ArticuloReventa> getArticulosReventa() {
		return reventaRepositorio.findAll();
	}
	
	public List<ArticuloReventa> getArticulosReventaF() {
		return reventaRepositorio.findByFechaBaja(null);
	}
	
	public List<ArticuloReventa> reventaSinStock() {
		
		List<ArticuloReventa> sinStock = new ArrayList<>();
		
		for(ArticuloReventa ar: this.getArticulosReventaF()) {
			if(ar.getStockActual() <= ar.getStockMinimo()) {
				
				sinStock.add(ar);
			}
		}
		return sinStock;
	}

	private String upload_folder = ".//src//main//resources//files//";

	public boolean saveFile(MultipartFile file, String denominacion, String venta, String compra, String min,
			String act, String medida, String id_rubro, String u_alta) throws IOException {

		if (!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(upload_folder + file.getOriginalFilename());
			Files.write(path, bytes);

			ArticuloReventa reventa = new ArticuloReventa();
			UnidadMedida u = medidaRepo.getOne(Integer.parseInt(medida));
			reventa.setDenominacion(denominacion);
			reventa.setPrecioCompra(Double.parseDouble(compra));
			reventa.setPrecioVenta(Double.parseDouble(venta));
			reventa.setStockMinimo(Double.parseDouble(min));
			reventa.setStockActual(Double.parseDouble(act));
			reventa.setUnidadMedida(u);
			reventa.setFechaAlta(new Date());
			reventa.setUsuarioCarga(u_alta);
			reventa.setImagen(file.getOriginalFilename());
			reventa.setRubroArticulo(rubroRepositorio.getOne(Integer.parseInt(id_rubro)));

			reventaRepositorio.save(reventa);

			return true;

		}
		return false;
	}

	public boolean uploadImage(MultipartFile file, String id) throws IOException {

		if (!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(upload_folder + file.getOriginalFilename());
			Files.write(path, bytes);

			ArticuloReventa reventa = reventaRepositorio.getOne(Integer.parseInt(id));
			reventa.setImagen(file.getOriginalFilename());
			reventaRepositorio.save(reventa);

			return true;

		}
		return false;
	}

	public Page<ArticuloReventa> paginas(Pageable pageable) {
		return reventaRepositorio.findByFechaBaja(null, pageable);
	}
	
	public Page<ArticuloReventa> paginasB(String buscado, Pageable pageable) {
		if(buscado == null) {
		return reventaRepositorio.findByFechaBaja(null, pageable);
		}else {
			return reventaRepositorio.findByFechaBajaAndDenominacionContaining(null, buscado, pageable);
		}
	}

	public Page<ArticuloReventa> paginass(int id, Pageable pageable) {
		RubroArticulo rubro = rubroRepositorio.findById(id);
		return reventaRepositorio.findByFechaBajaAndRubroArticulo(null, rubro, pageable);
	}

	public List<ArticuloReventa> getReventa(int id) {

		List<RubroArticulo> rubros = new ArrayList<RubroArticulo>();
		List<ArticuloReventa> arr = new ArrayList<ArticuloReventa>();
		metodoRecursivo(rubros, id);

		for (RubroArticulo ra : rubros) {

			List<ArticuloReventa> revent = reventaRepositorio.findByFechaBajaAndRubroArticulo(null, ra);
			if (revent != null) {
				for (ArticuloReventa ar : revent) {
					arr.add(ar);
				}

			}

		}

		return arr;
	}

	public void metodoRecursivo(List<RubroArticulo> rubro, int id) {

		RubroArticulo r = rubroRepositorio.findById(id);
		rubro.add(r);
		List<RubroArticulo> hijos = rubroRepositorio.findByRubro(r);
		if (hijos != null) {
			for (RubroArticulo ra : hijos) {
				metodoRecursivo(rubro, ra.getId());
			}

		}

	}

}
