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

import com.example.entitie.Rol;
import com.example.service.RolService;

@RestController
@RequestMapping("/rol")

public class RolController {
	
	@Autowired
	@Qualifier("rolservicio")
	private RolService rolServ;
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/agregar")
	public boolean agregar(@RequestBody @Valid Rol rol) {
		return rolServ.crear(rol);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/roles")
	public List<Rol> getRoles(){
		
		return rolServ.getRoles();
		
	}

}
