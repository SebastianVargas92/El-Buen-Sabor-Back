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

import com.example.entitie.Factura;
import com.example.service.FacturaService;

@RestController
@RequestMapping("/factura")
public class FacturaController {
	
	@Autowired
	@Qualifier("facturaservicio")
	private FacturaService facturaServ;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/agregar")
	public boolean agregar(@RequestBody @Valid Factura factura) {
		return facturaServ.crear(factura);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/pages")
	public ResponseEntity<Page<Factura>> paginas(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "4") int size) {
		Page<Factura> facturas = facturaServ.paginas(PageRequest.of(page, size));

		return new ResponseEntity<Page<Factura>>(facturas, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/facturas")
	public List<Factura> getFacturas() {
		List<Factura> facturas = facturaServ.getFacturas();

		return facturas;
	}

}
