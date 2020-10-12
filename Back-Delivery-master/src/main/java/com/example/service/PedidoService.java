package com.example.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.entitie.ArticuloManufacturado;
import com.example.entitie.Cliente;
import com.example.entitie.DetallePedido;
import com.example.entitie.Pedido;
import com.example.entitie.PlatoVendido;
import com.example.repository.ClienteRepository;
import com.example.repository.PedidoRepository;


@Service("pedidoservicio")
public class PedidoService {
	
	List<PlatoVendido> platos = null;
	
	@Autowired
	@Qualifier("servicio")
	private ClienteService service;
	
	@Autowired
	@Qualifier("pedidorepositorio")
	private PedidoRepository pedidoRepo;

	@Autowired
	@Qualifier("repositorio")
	private ClienteRepository repository;

	private static final Log logger = LogFactory.getLog(ClienteService.class);

	public boolean crear(Pedido pedido) {

		logger.info("Agregando Pedido");
		try {
			pedidoRepo.save(pedido);
			logger.info("Pedido Guardado");
			return true;
		} catch (Exception e) {
			logger.info("Error al agregar Pedido " + e);
			return false;
		}
	}

	public List<Pedido> getPedidos() {
		return pedidoRepo.findAll();
	}
	
	public List<Pedido> pedidosCliente(String desde, String hasta, String correo) throws ParseException{
		
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		Date desdee = formater.parse(desde);
		Date hastaa = formater.parse(hasta);
		
		Cliente c = this.service.getCliente(correo);
		return this.pedidoRepo.findByFechaBetweenAndCliente(desdee, hastaa, c);
	}
	
public List<Pedido> pedidosIngresos(String desde, String hasta) throws ParseException{
		
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		Date desdee = formater.parse(desde);
		Date hastaa = formater.parse(hasta);
		
		return this.pedidoRepo.findByFechaBetweenAndEstado(desdee, hastaa, 5);
	}


	public List<PlatoVendido> masVendidas(String desde, String hasta) throws ParseException {

		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		Date desdee = formater.parse(desde);
		Date hastaa = formater.parse(hasta);
		this.platos = null;

		// return this.pedidoRepo.findByFechaBetween(desdee, hastaa);
		// return this.pedidoRepo.findByFechaBetweenAndEstado(desdee, hastaa, 5);

		List<Pedido> pedidosFF = this.pedidoRepo.findByFechaBetweenAndEstado(desdee, hastaa, 5);
		List<ArticuloManufacturado> m = new ArrayList<ArticuloManufacturado>();
		for (Pedido p : pedidosFF) {
			for (DetallePedido dp : p.getDetallePedido()) {
				if (dp.getArticuloManufacturado() != null) {
					m.add(dp.getArticuloManufacturado());
				}
			}
		}
		
		//List<Platos> platos = null;
		
		for(ArticuloManufacturado am : m ) {
			if(platos == null) {
				platos = new ArrayList<PlatoVendido>();
				PlatoVendido p = new PlatoVendido();
				p.setArticulo(am);
				p.setCantidad(1);
				platos.add(p);
			}else {
				if(this.existe(am) == false) {
					PlatoVendido p = new PlatoVendido();
					p.setArticulo(am);
					p.setCantidad(1);
					platos.add(p);
				}
			}
		}
		
		platos.sort(Comparator.comparing(PlatoVendido::getCantidad).reversed());
		return platos;
	}
	
	
	public boolean existe(ArticuloManufacturado manu) {
		if(platos != null) {
		for(PlatoVendido p : platos) {
			if(p.getArticulo() != null) {
			if(p.getArticulo() == manu) {
				p.setCantidad(p.getCantidad() + 1);;
				return true;
			}
			}
		}
		}
		
		return false;
		
	}
	
	
	public Page<Pedido> paginas(int estado, Pageable pageable) {
		logger.info("El estado es : " + estado);
		return pedidoRepo.findByEstado(estado, pageable);
	}

	public Page<Pedido> paginasC(String correo, Pageable pageable) {
		Cliente c = repository.findByCorreo(correo);
		return pedidoRepo.findByCliente(c, pageable);
	}

	public Page<Pedido> paginasCyE(String correo, int estado, Pageable pageable) {
		Cliente c = repository.findByCorreo(correo);
		return pedidoRepo.findByClienteAndEstado(c, estado, pageable);
	}

	public Pedido getPedidoNum(long numero) {
		return this.pedidoRepo.findByNumero(numero);
	}

	public List<Pedido> getPedidosCocina() {
		return this.pedidoRepo.findByEstado(2);
	}
	
}
