package com.example.entitie;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Configuracion")
public class Configuracion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="emailEmpresa")
	private String emailEmpresa;
	
	@Column(name="cantCocineros")
	private int cantCocineros;
	
	
	public Configuracion() {
		
	}
	

	public Configuracion(int id, String emailEmpresa, int cantCocineros) {
		super();
		this.id = id;
		this.emailEmpresa = emailEmpresa;
		this.cantCocineros = cantCocineros;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getEmailEmpresa() {
		return emailEmpresa;
	}


	public void setEmailEmpresa(String emailEmpresa) {
		this.emailEmpresa = emailEmpresa;
	}


	public int getCantCocineros() {
		return cantCocineros;
	}


	public void setCantCocineros(int cantCocineros) {
		this.cantCocineros = cantCocineros;
	}
	
}
