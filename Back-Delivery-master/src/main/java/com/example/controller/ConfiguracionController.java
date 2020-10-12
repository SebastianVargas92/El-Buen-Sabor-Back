package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entitie.Configuracion;
import com.example.service.ConfiguracionService;

@RestController
@RequestMapping("/configuracion")
public class ConfiguracionController {
	
	@Autowired
	@Qualifier("configuracionservicio")
	private ConfiguracionService service;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/agregar")
	public boolean agregar(@RequestBody @Valid Configuracion configuracion) {
		return service.crear(configuracion);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/listar")
	public List<Configuracion> getConfiguraciones() {
		return service.getConfiguraciones();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/configuracion/{id}")
	public Configuracion getConfig(@PathVariable int id) {
		return service.getConfiguracion(id);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/configuracionn/{correo}")
	public boolean getConfig(@PathVariable String correo) {
		
		Configuracion c = service.getConfiguracionEmail(correo);
		c.setCantCocineros(c.getCantCocineros() +1);
		service.crear(c);
		return true;
	}
	
}
