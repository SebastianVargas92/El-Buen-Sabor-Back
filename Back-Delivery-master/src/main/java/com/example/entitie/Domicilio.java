package com.example.entitie;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Domicilio")
public class Domicilio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "calle")
	private String calle;
	/*
	@Column(name = "localidad")
	private String localidad;
	
	*/
	@ManyToOne()
	@JoinColumn(name = "id_localidad", referencedColumnName = "id")
	private Localidad localidad;

	@Column(name = "numero")
	private int numero;

	@OneToOne(mappedBy = "domicilio", fetch = FetchType.LAZY)
	private Cliente cliente;

	public Domicilio() {
	}

	public Domicilio(int id, String calle, int numero) {
		this.id = id;
		this.calle = calle;
	
		this.numero = numero;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}
	
	/*

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	*/
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
}
