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

import com.example.entitie.RubroGeneral;
import com.example.service.RubroService;

@RestController
@RequestMapping("/rubro")
public class RubroControlle {

	@Autowired
	@Qualifier("rubroservicio")
	private RubroService rubroService;

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/agregar")
	public boolean agregar(@RequestBody @Valid RubroGeneral rubro) {
		return rubroService.crear(rubro);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/rubros")
	public List<RubroGeneral> getRubros() {

		return rubroService.getRubros();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/rubro")
	public RubroGeneral getRubro(@RequestParam(defaultValue = "0") int rubro) {

		return rubroService.getRubro(rubro);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/pages")
	public ResponseEntity<Page<RubroGeneral>> paginas(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "4") int size) {
		Page<RubroGeneral> rubros = rubroService.paginas(PageRequest.of(page, size));

		return new ResponseEntity<Page<RubroGeneral>>(rubros, HttpStatus.OK);
	}

}
