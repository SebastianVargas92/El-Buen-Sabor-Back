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

import com.example.entitie.Localidad;
import com.example.service.LocalidadService;

@RestController
@RequestMapping("/localidad")
public class LocalidadController {
	
	@Autowired
	@Qualifier("localidadserivicio")
	private LocalidadService locService;
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/agregar")
	public boolean agregar(@RequestBody @Valid Localidad localidad) {
		return locService.crear(localidad);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/listar")
	public List<Localidad> getLocaliades() {
		return locService.getLocalidades();
	}
	
	

}
