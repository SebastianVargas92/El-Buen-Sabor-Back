package com.example.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.validation.Valid;

import org.springframework.http.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.example.entitie.ArticuloInsumo;
import com.example.entitie.ArticuloReventa;
import com.example.entitie.Configuracion;
import com.example.entitie.DetallePedido;
import com.example.entitie.Pedido;
import com.example.entitie.PlatoVendido;
import com.example.service.ConfiguracionService;
import com.example.service.GenerateExcel;
import com.example.service.GeneratePdfRepor;
import com.example.service.InsumoService;
import com.example.service.PedidoService;
import com.example.service.ReventaService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	@Qualifier("pedidoservicio")
	private PedidoService pedidoServ;

	@Autowired
	@Qualifier("configuracionservicio")
	private ConfiguracionService confServ;
	
	@Autowired
	@Qualifier("insumoservicio")
	private InsumoService insumoService;
	
	@Autowired
	@Qualifier("reventaservicio")
	private ReventaService reventaService;

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/agregar")
	public boolean agregar(@RequestBody @Valid Pedido pedido) {
		if (pedido.getFecha() == null) {
			pedido.setFecha(new Date());
		}

		if (pedido.getEstado() == 2) {
			if (pedido.getHoraEstimadaFin() == null) {

				pedido.setHoraEstimadaFin(new Date());
				Date entrega = pedido.getHoraEstimadaFin();

				int sumatoriaManu = 0;

				for (DetallePedido dp : pedido.getDetallePedido()) {

					if (dp.getArticuloManufacturado() != null) {
						sumatoriaManu += dp.getArticuloManufacturado().getTiempoEstimadoCocina() * dp.getCantidad();
					}
				}

				sumatoriaManu += this.minutosPedidosEnCocina();

				// Configuracion c =
				// this.confServ.getConfiguracionEmail("unapersonamisteriosa1@gmail.com");
				// int cocineros = c.getCantCocineros();

				// sumatoriaManu = sumatoriaManu / cocineros;

				if (pedido.getTipoEnvio() == 2) {
					sumatoriaManu += 10;
				}
				entrega.setMinutes(entrega.getMinutes() + sumatoriaManu);
				pedido.setHoraEstimadaFin(entrega);

			} else {
				pedido.getHoraEstimadaFin().setMinutes(pedido.getHoraEstimadaFin().getMinutes() + 10);
			}

		}

		return pedidoServ.crear(pedido);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/pages")
	public ResponseEntity<Page<Pedido>> paginas(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "4") int size,
			@RequestParam(defaultValue = "true") boolean asc,
			@RequestParam(defaultValue = "1") int estado) {
		
		Page<Pedido> pedidos = null;
		if(asc) {
			pedidos = pedidoServ.paginas(estado, PageRequest.of(page, size, Sort.by("fecha").ascending()));
		}else {
			pedidos = pedidoServ.paginas(estado, PageRequest.of(page, size, Sort.by("fecha").descending()));
		}
		 

		return new ResponseEntity<Page<Pedido>>(pedidos, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/pagess")
	public ResponseEntity<Page<Pedido>> paginasC(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "4") int size, @RequestParam(defaultValue = "null") String correo) {
		Page<Pedido> pedidos = pedidoServ.paginasC(correo, PageRequest.of(page, size));
		return new ResponseEntity<Page<Pedido>>(pedidos, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/pagesss")
	public ResponseEntity<Page<Pedido>> paginasCyE(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "4") int size, @RequestParam(defaultValue = "1") int estado,
			@RequestParam(defaultValue = "null") String correo) {
		Page<Pedido> pedidos = pedidoServ.paginasCyE(correo, estado, PageRequest.of(page, size));

		return new ResponseEntity<Page<Pedido>>(pedidos, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/pedidos")
	public List<Pedido> getPedidos() {
		List<Pedido> pedidos = pedidoServ.getPedidos();

		return pedidos;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/pedido/{numero}")
	public Pedido getPedidoNum(@PathVariable long numero) {
		return this.pedidoServ.getPedidoNum(numero);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/masvendidas")
	public List<PlatoVendido> masVendidas(@RequestPart String desde, @RequestPart String hasta) throws ParseException {
		return this.pedidoServ.masVendidas(desde, hasta);

	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/pedidoscliente")
	public List<Pedido> pedidosCliente(@RequestPart String desde, @RequestPart String hasta, @RequestPart String correo) throws ParseException {
		return this.pedidoServ.pedidosCliente(desde, hasta, correo);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/pedidoingresos")
	public List<Pedido> pedidosingresos(@RequestPart String desde, @RequestPart String hasta) throws ParseException {
		return this.pedidoServ.pedidosIngresos(desde, hasta);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/pdfreport", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> pdfReport(@RequestParam long numero) {

		Pedido p = this.pedidoServ.getPedidoNum(numero);

		ByteArrayInputStream bis = GeneratePdfRepor.citiesReport(p);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=facturareport.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/pedidosclienteexcel")
    public ResponseEntity<InputStreamResource> excelPClienteReport(@RequestParam String desde,
			@RequestParam String hasta, @RequestParam String correo) throws IOException, ParseException {
		
        List<Pedido> pedidos = this.pedidoServ.pedidosCliente(desde, hasta, correo);;
    
    ByteArrayInputStream in =  GenerateExcel.pedidoClienteExcel(pedidos,desde,hasta);
    		
    HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=pedidoCliente.xlsx");
    
     return ResponseEntity
                  .ok()
                  .headers(headers)
                  .body(new InputStreamResource(in));
    }
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/maspedidasexcel")
    public ResponseEntity<InputStreamResource> excelMasPedidas(@RequestParam String desde,
			@RequestParam String hasta) throws IOException, ParseException {
		
       List<PlatoVendido> masPedidas = this.pedidoServ.masVendidas(desde, hasta);
    
    ByteArrayInputStream in =  GenerateExcel.masPedidasExcel(masPedidas, desde,hasta);
    		
    HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=masVendidas.xlsx");
    
     return ResponseEntity
                  .ok()
                  .headers(headers)
                  .body(new InputStreamResource(in));
    }
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/sinstockexcel")
    public ResponseEntity<InputStreamResource> excelPClienteReport() throws IOException, ParseException {
		
        List<ArticuloInsumo> insumos = this.insumoService.insumoSinStock();
        
        List<ArticuloReventa> reventas = this.reventaService.reventaSinStock();
    
    ByteArrayInputStream in =  GenerateExcel.sinStockExcel(insumos, reventas);
    		
    HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=sinStock.xlsx");
    
     return ResponseEntity
                  .ok()
                  .headers(headers)
                  .body(new InputStreamResource(in));
    }
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/ingresosExcel")
    public ResponseEntity<InputStreamResource> excelIngresos(@RequestParam String desde,
			@RequestParam String hasta) throws IOException, ParseException {
		
       List<Pedido> pedidos = this.pedidoServ.pedidosIngresos(desde, hasta);
    
    ByteArrayInputStream in =  GenerateExcel.ingresosExcel(pedidos, desde,hasta);
    		
    HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=ingresos.xlsx");
    
     return ResponseEntity
                  .ok()
                  .headers(headers)
                  .body(new InputStreamResource(in));
    }

	public int minutosPedidosEnCocina() {
		List<Pedido> pc = this.pedidoServ.getPedidosCocina();
		int sumaTPedidoCocina = 0;
		if (pc != null) {
			for (Pedido p : pc) {
				for (DetallePedido dp : p.getDetallePedido()) {
					if (dp.getArticuloManufacturado() != null) {
						sumaTPedidoCocina += dp.getArticuloManufacturado().getTiempoEstimadoCocina() * dp.getCantidad();
					}
				}
			}
		}

		Configuracion c = this.confServ.getConfiguracionEmail("unapersonamisteriosa1@gmail.com");
		int cocineros = c.getCantCocineros();

		sumaTPedidoCocina = sumaTPedidoCocina / cocineros;
		return sumaTPedidoCocina;
	}

}
