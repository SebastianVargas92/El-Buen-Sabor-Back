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

import com.example.entitie.DetallePedido;
import com.example.service.DetallePedidoService;

@RestController
@RequestMapping("/detalle")
public class DetallePedidoController {
	
	@Autowired
	@Qualifier("detallePedidoServicio")
	private DetallePedidoService detalleServ;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/agregar")
	public boolean agregar(@RequestBody @Valid DetallePedido detalle) {
		return detalleServ.crear(detalle);
	}
	
	@GetMapping("/detalles")
	public List<DetallePedido> getDetalles(){
		return detalleServ.getDetallePedidos();
	}

}
