package com.example.entitie;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "ArticuloManufacturadoDetalle")
public class ArticuloManufacturadoDetalle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "cantidad")
	private double cantidad;

	@ManyToOne()
	@JoinColumn(name = "id_unidad_medida", referencedColumnName = "id")
	private UnidadMedida unidadMedida;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_manufacturado")
	@JsonBackReference
	private ArticuloManufacturado manufacturado;

	@ManyToOne()
	@JoinColumn(name = "id_insumo", referencedColumnName = "id_control")
	private ArticuloInsumo articuloInsumo;

	public ArticuloManufacturadoDetalle() {

	}

	public ArticuloManufacturadoDetalle(int id, int cantidad) {

		this.id = id;
		this.cantidad = cantidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public ArticuloManufacturado getManufacturado() {
		return manufacturado;
	}

	public void setManufacturado(ArticuloManufacturado manufacturado) {
		this.manufacturado = manufacturado;
	}

	public ArticuloInsumo getArticuloInsumo() {
		return articuloInsumo;
	}

	public void setArticuloInsumo(ArticuloInsumo articuloInsumo) {
		this.articuloInsumo = articuloInsumo;
	}

	public UnidadMedida getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(UnidadMedida unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

}
