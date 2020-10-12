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
import com.example.entitie.ArticuloManufacturado;
import com.example.service.ManufacturadoService;

@RestController
@RequestMapping("/manufacturado")
public class ManufacturadoController {

	@Autowired
	@Qualifier("manufacturadoservicio")
	private ManufacturadoService manufacturadoService;

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/agregar")
	public boolean agregar(@RequestBody @Valid ArticuloManufacturado manufacturado) {
		return manufacturadoService.crear(manufacturado);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/listar")
	public List<ArticuloManufacturado> getManufacturados() {
		return manufacturadoService.getManufacturados();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/nuevo")
	public boolean uploadFile(@RequestParam("file") MultipartFile file) {

		if (file.isEmpty()) {
			return false;
		}

		try {
			return manufacturadoService.saveFile(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
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
	public boolean uploadImage(@RequestParam("file") MultipartFile file, @RequestPart String id) {
		if (file.isEmpty()) {
			return false;
		}
		try {
			return manufacturadoService.uploadImage(file, id);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/pages")
	public ResponseEntity<Page<ArticuloManufacturado>> paginas(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "4") int size) {
		Page<ArticuloManufacturado> insumos = manufacturadoService.paginas(PageRequest.of(page, size));

		return new ResponseEntity<Page<ArticuloManufacturado>>(insumos, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/pagess")
	public ResponseEntity<Page<ArticuloManufacturado>> paginass(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "4") int size,
			@RequestParam(defaultValue = "id") String order,
			@RequestParam(defaultValue = "true") boolean asc,
			@RequestParam(defaultValue = "0") int rubro) {
		Page<ArticuloManufacturado> insumos;
		if (rubro == 0) {
			if(asc) {
				insumos = manufacturadoService.paginas(PageRequest.of(page, size, Sort.by(order)));
			}else {
				insumos = manufacturadoService.paginas(PageRequest.of(page, size, Sort.by(order).descending()));
			}
			
		} else {
			if(asc) {
				insumos = manufacturadoService.paginass(rubro, PageRequest.of(page, size, Sort.by(order)));
			}else {
				insumos = manufacturadoService.paginass(rubro, PageRequest.of(page, size, Sort.by(order).descending()));}
			
		}
		return new ResponseEntity<Page<ArticuloManufacturado>>(insumos, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/pagesss")
	public ResponseEntity<Page<ArticuloManufacturado>> paginassB(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "4") int size,
			@RequestParam(defaultValue = "id") String order,
			@RequestParam(defaultValue = "true") boolean asc,
			@RequestParam(defaultValue = "0") int rubro,
			@RequestParam(defaultValue = "null") String termino) {

		Page<ArticuloManufacturado> insumos;

		if (rubro == 0 && termino == null) {
			if (asc) {
				insumos = manufacturadoService.paginas(PageRequest.of(page, size, Sort.by(order)));
			} else {
				insumos = manufacturadoService.paginas(PageRequest.of(page, size, Sort.by(order).descending()));
			}
		} else if (rubro == 0 && termino != null) {
			if (asc) {
				insumos = manufacturadoService.paginasB(termino, PageRequest.of(page, size, Sort.by(order)));
			} else {
				insumos = manufacturadoService.paginasB(termino,
						PageRequest.of(page, size, Sort.by(order).descending()));
			}
		} else {
			if (asc) {
				insumos = manufacturadoService.paginassB(rubro, termino, PageRequest.of(page, size, Sort.by(order)));
			} else {
				insumos = manufacturadoService.paginassB(rubro, termino,
						PageRequest.of(page, size, Sort.by(order).descending()));
			}
		}
		return new ResponseEntity<Page<ArticuloManufacturado>>(insumos, HttpStatus.OK);
	}
}
