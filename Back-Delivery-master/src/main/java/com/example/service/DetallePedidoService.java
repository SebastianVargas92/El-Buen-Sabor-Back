package com.example.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.entitie.DetallePedido;
import com.example.repository.DetallePedidoRepository;

@Service("detallePedidoServicio")
public class DetallePedidoService {

	@Autowired
	@Qualifier("detallePedidoRepositorio")
	private DetallePedidoRepository detalleRepo;
	
	
	private static final Log logger = LogFactory.getLog(ClienteService.class);

	public boolean crear(DetallePedido detalle){

		logger.info("Agregando Detalle Pedido");
		try {
			detalleRepo.save(detalle);
			logger.info("Detalle Pedido Guardado");
			return true;
		} catch (Exception e) {
			logger.info("Error al agregar Manufacturado " + e);
			return false;
		}
	}
	
	public List<DetallePedido> getDetallePedidos(){
		return detalleRepo.findAll();
	}
}
