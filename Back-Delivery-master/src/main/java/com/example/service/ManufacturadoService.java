package com.example.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.entitie.ArticuloManufacturado;
import com.example.entitie.RubroGeneral;
import com.example.repository.ManufacturadoRepository;
import com.example.repository.RubroRepository;

@Service("manufacturadoservicio")
public class ManufacturadoService {

	@Autowired
	@Qualifier("manufacturadorepositorio")
	private ManufacturadoRepository manufacturadoRepositorio;
	
	@Autowired
	@Qualifier("rubrorepositorio")
	private RubroRepository rubroRepositorio;

	private static final Log logger = LogFactory.getLog(ClienteService.class);

	public boolean crear(ArticuloManufacturado manufacturado) {

		logger.info("Agregando Manufacturado");
		try {
			manufacturadoRepositorio.save(manufacturado);
			logger.info("Manufacturado Guardado");
			return true;
		} catch (Exception e) {
			logger.info("Error al agregar Manufacturado " + e);
			return false;
		}
	}
	
	public List<ArticuloManufacturado> getManufacturados() {
		return manufacturadoRepositorio.findAll();
	}
	
	private String upload_folder = ".//src//main//resources//files//";

    public boolean saveFile(MultipartFile file) throws IOException {
    	
        if(!file.isEmpty()){
            byte[] bytes = file.getBytes();
            Path path = Paths.get(upload_folder + file.getOriginalFilename());
            Files.write(path,bytes);
            
        	return true;
        }
        return false;
    }
    
    public boolean uploadImage(MultipartFile file, String id) throws IOException {

		if (!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(upload_folder + file.getOriginalFilename());
			Files.write(path, bytes);

			ArticuloManufacturado manufacturado  = manufacturadoRepositorio.findByDenominacion(id);
			manufacturado.setImage(file.getOriginalFilename());
			manufacturadoRepositorio.save(manufacturado);

			return true;
		}
		return false;
	}
    
    public Page<ArticuloManufacturado> paginas(Pageable pageable) {
		return manufacturadoRepositorio.findByFechaBaja(null, pageable);
	}
    
    public Page<ArticuloManufacturado> paginasB(String buscado, Pageable pageable) {
		return manufacturadoRepositorio.findByFechaBajaAndDenominacionContaining(null, buscado, pageable);
	}
    
    
    public Page<ArticuloManufacturado> paginass(int id,Pageable pageable) {
    	RubroGeneral rubro = rubroRepositorio.findById(id);
    	
		return manufacturadoRepositorio.findByFechaBajaAndRubro(null,rubro, pageable);
	}
    
    public Page<ArticuloManufacturado> paginassB(int id, String buscado, Pageable pageable) {
    	RubroGeneral rubro = rubroRepositorio.findById(id);
    	
		return manufacturadoRepositorio.findByFechaBajaAndRubroAndDenominacionContaining(null, rubro, buscado, pageable);
	}
}
