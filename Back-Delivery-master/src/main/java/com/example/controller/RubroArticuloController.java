package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entitie.RubroArticulo;
import com.example.service.RubroArticuloService;

@RestController
@RequestMapping("/rubroarticulo")
public class RubroArticuloController {

	@Autowired
	@Qualifier("rubroarticuloservicio")
	private RubroArticuloService rubroArticuloServicio;

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/agregar")
	public boolean agregar(@RequestBody @Valid RubroArticulo rubro) {
		return rubroArticuloServicio.crear(rubro);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/rubros")
	public List<RubroArticulo> getRubros() {

		return rubroArticuloServicio.getRubrosArticulo();

	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/rubross")
	public List<RubroArticulo> getRubrosR(@RequestParam int id) {
		return rubroArticuloServicio.getRubrosArticuloRRR(id);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/rubro")
	public RubroArticulo getRubro(@RequestParam int id) {

		return rubroArticuloServicio.getRubro(id);

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/pages")
	public ResponseEntity<Page<RubroArticulo>> paginas(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "4") int size) {
		Page<RubroArticulo> rubros = rubroArticuloServicio.paginas(PageRequest.of(page, size));

		return new ResponseEntity<Page<RubroArticulo>>(rubros, HttpStatus.OK);
	}

}
