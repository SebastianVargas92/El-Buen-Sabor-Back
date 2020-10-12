package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entitie.ArticuloInsumo;
import com.example.service.InsumoService;

@RestController
@RequestMapping("/insumo")
public class InsumoController {

	@Autowired
	@Qualifier("insumoservicio")
	private InsumoService insumoService;

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/agregar")
	public boolean agregar(@RequestBody @Valid ArticuloInsumo insumo) {
		return insumoService.crear(insumo);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/insumos")
	public List<ArticuloInsumo> getInsumos() {

		return insumoService.getInsumos();

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/sinstock")
	public List<ArticuloInsumo> getInsumoSinStock() {

		return insumoService.insumoSinStock();

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/pages")
	public ResponseEntity<Page<ArticuloInsumo>> paginas(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "4") int size,
			@RequestParam(defaultValue = "id") String order,
			@RequestParam(defaultValue = "true") boolean asc) {

		Page<ArticuloInsumo> insumos = null;

		if (asc) {
			insumos = insumoService.paginas(PageRequest.of(page, size, Sort.by(order)));
		} else {
			insumos = insumoService.paginas(PageRequest.of(page, size, Sort.by(order).descending()));
		}
		return new ResponseEntity<Page<ArticuloInsumo>>(insumos, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/pagess")
	public ResponseEntity<Page<ArticuloInsumo>> insumoB(@
			RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "4") int size,
			@RequestParam(defaultValue = "id") String order,
			@RequestParam(defaultValue = "true") boolean asc,
			@RequestParam(defaultValue = "null") String buscado) {
		
		Page<ArticuloInsumo> insumos = null;
		if(asc) {
			 insumos = insumoService.paginasB(buscado, PageRequest.of(page, size, Sort.by(order)));
		}else {
			 insumos = insumoService.paginasB(buscado, PageRequest.of(page, size, Sort.by(order).descending()));
		}
		//Page<ArticuloInsumo>
		return new ResponseEntity<Page<ArticuloInsumo>>(insumos, HttpStatus.OK);
	}

}
