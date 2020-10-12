package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.entitie.Rol;
import com.example.repository.RolRepository;

@Service("rolservicio")
public class RolService {
	
	@Autowired
	@Qualifier("rolrepositorio")
	private RolRepository rolRepo;
	
	public boolean crear(Rol rol) {

		
		try {
			rolRepo.save(rol);
			
			return true;
		} catch (Exception e) {
			
			return false;
		}

	}
	
	public List<Rol> getRoles(){
		return rolRepo.findAll();

	}

}
