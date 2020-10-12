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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entitie.Cliente;
import com.example.entitie.Rol;
import com.example.service.ClienteService;

@RestController
@RequestMapping("/v1")
public class ClienteController {
	
	@Autowired
	@Qualifier("servicio")
	private ClienteService service;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/agregar")
	public boolean agregar(@RequestBody @Valid Cliente cliente) {
		return service.crear(cliente);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/registrar")
	public boolean registrar(@RequestBody @Valid Cliente cliente) {
		return service.registrar(cliente);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/clienterol")
	public List<Cliente> getClienteRol(@RequestBody @Valid Rol rol) {
		return service.getClientesRol(rol);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/actualizar")
	public boolean actualizar(@RequestBody @Valid Cliente cliente) {
		return service.actualizar(cliente);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/clientes")
	public List<Cliente> getClientes(){
		
		return service.getClientes();
		
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/cliente/{correo}")
	@Transactional
	public Cliente getOne(@PathVariable String correo) {
			return service.getCliente(correo);
		}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/pages")
    public ResponseEntity<Page<Cliente>> paginas(
    		 @RequestParam(defaultValue = "0") int page,
             @RequestParam(defaultValue = "4") int size
             ){
        Page<Cliente> clientes = service.paginas(PageRequest.of(page, size));
       
        return new ResponseEntity<Page<Cliente>>(clientes, HttpStatus.OK);
    }
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/pagess")
    public ResponseEntity<Page<Cliente>> paginasR(
    		 @RequestParam(defaultValue = "0") int page,
             @RequestParam(defaultValue = "4") int size,
             @RequestParam(defaultValue = "null") String order,
             @RequestParam(defaultValue = "true") boolean asc,
             @RequestParam(defaultValue = "0") int rol
             ){
		
        Page<Cliente> usuarios = null;
        
        if(asc) {
        	usuarios = service.paginasR(rol, PageRequest.of(page, size, Sort.by(order)));
        }else {
        	usuarios = service.paginasR(rol, PageRequest.of(page, size, Sort.by(order).descending()));
        }
       
        return new ResponseEntity<Page<Cliente>>(usuarios, HttpStatus.OK);
    }

	}

