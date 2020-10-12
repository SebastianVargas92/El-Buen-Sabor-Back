package com.example.entitie;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "RubroArticulo")
@PrimaryKeyJoinColumn(name = "id_control")
public class RubroArticulo extends Control {

	private static final long serialVersionUID = 1L;

	@Column(name = "denominacion")
	private String denominacion;

	@ManyToOne()
	@JoinColumn(name = "id_rubro", referencedColumnName = "id_control")
	private RubroArticulo rubro;

	public RubroArticulo() {
	}

	public RubroArticulo(String denominacion) {
		this.denominacion = denominacion;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public RubroArticulo getRubro() {
		return rubro;
	}

	public void setRubro(RubroArticulo rubro) {
		this.rubro = rubro;
	}

}
