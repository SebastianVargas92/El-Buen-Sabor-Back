package com.example.entitie;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "RubroGeneral")
@PrimaryKeyJoinColumn(name = "id_control")
public class RubroGeneral extends Control {

	private static final long serialVersionUID = 1L;

	@Column(name = "denominacion")
	private String denominacion;

	public RubroGeneral() {
	}

	public RubroGeneral(String denominacion) {
		this.denominacion = denominacion;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

}
