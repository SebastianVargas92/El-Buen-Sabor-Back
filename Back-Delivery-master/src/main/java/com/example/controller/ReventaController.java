package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import javax.activation.FileTypeMap;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.entitie.ArticuloReventa;
import com.example.service.ReventaService;

@RestController
@RequestMapping("/reventa")
public class ReventaController {

	@Autowired
	@Qualifier("reventaservicio")
	private ReventaService reventaService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/actualizar")
	public boolean agregar(@RequestBody @Valid ArticuloReventa reventa) {
		return reventaService.actualizar(reventa);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/nuevo")
	public boolean uploadFile(@RequestPart String denominacion, @RequestPart String u_alta,
			@RequestPart String precio_compra, @RequestPart String precio_venta, @RequestPart String actual,
			@RequestPart String minimo, @RequestPart String medida, @RequestPart String id_rubro,
			@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return false;
			//return new ResponseEntity<Object>("Seleccionar un archivo", HttpStatus.OK);
		}

		try {
			return reventaService.saveFile(file, denominacion, precio_venta, precio_compra, minimo, actual, medida, id_rubro,
					u_alta);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		//return new ResponseEntity<Object>("Archivo subido correctamente", HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("imagen/{nombre}")
	public ResponseEntity<byte[]> getImage(@PathVariable String nombre) throws IOException {
		File img = new File("src/main/resources/files/" + nombre);
		return ResponseEntity.ok()
				.contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img)))
				.body(Files.readAllBytes(img.toPath()));
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/image")
	public boolean uploadImage( @RequestParam("file") MultipartFile file,@RequestPart String id) {
		if (file.isEmpty()) {
			return false;
		}
		try {
			return reventaService.uploadImage(file,id);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/listar")
	public List<ArticuloReventa> getReventa() {

		return reventaService.getArticulosReventa();

	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/sinstock")
	public List<ArticuloReventa> getReventaSinStock() {

		return reventaService.reventaSinStock();

	}


	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/pages")
	public ResponseEntity<Page<ArticuloReventa>> paginas(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "4") int size,
			@RequestParam(defaultValue = "id") String order,
            @RequestParam(defaultValue = "true") boolean asc) {
		
		Page<ArticuloReventa> insumos = null;
		if(asc) {
			insumos = reventaService.paginas(PageRequest.of(page, size, Sort.by(order)));
		}else {
			insumos = reventaService.paginas(PageRequest.of(page, size, Sort.by(order).descending()));
		}
		

		return new ResponseEntity<Page<ArticuloReventa>>(insumos, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/pagess")
	public ResponseEntity<Page<ArticuloReventa>> paginass(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "4") int size,
			@RequestParam(defaultValue = "0") int rubro) {
		Page<ArticuloReventa> reventa;
		if (rubro == 0) {
			reventa = reventaService.paginas(PageRequest.of(page, size));
		} else {
			reventa = reventaService.paginass(rubro, PageRequest.of(page, size));
		}
		return new ResponseEntity<Page<ArticuloReventa>>(reventa, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/pagesss")
	public ResponseEntity<Page<ArticuloReventa>> paginasB(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "4") int size,
			@RequestParam(defaultValue = "id") String order,
            @RequestParam(defaultValue = "true") boolean asc,
			@RequestParam(defaultValue = "null") String termino) {
		Page<ArticuloReventa> insumos = null;
		if(asc) {
			insumos = reventaService.paginasB(termino, PageRequest.of(page, size, Sort.by(order)));
		}else {
		    insumos = reventaService.paginasB(termino, PageRequest.of(page, size, Sort.by(order).descending()));
		}
		
		return new ResponseEntity<Page<ArticuloReventa>>(insumos, HttpStatus.OK);
	}
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/listarr")
	public List<ArticuloReventa> getReventaa(@RequestParam(defaultValue = "0") int rubro) {

		if(rubro == 0) {
		return reventaService.getArticulosReventa();
		}else {
			return reventaService.getReventa(rubro);
		}
	}

}
