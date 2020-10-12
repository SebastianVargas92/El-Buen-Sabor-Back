package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entitie.UnidadMedida;
import com.example.service.UnidadMedidaService;

@RestController
@RequestMapping("/medida")
public class UnidadMedidaController {

	@Autowired
	@Qualifier("unidadmedidaservicio")
	private UnidadMedidaService medidaServ;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/agregar")
	public boolean agregar(@RequestBody @Valid UnidadMedida medida) {
		return medidaServ.crear(medida);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/listar")
	public List<UnidadMedida> getMedidas() {
		return medidaServ.getMedidas();
	}
	
}
