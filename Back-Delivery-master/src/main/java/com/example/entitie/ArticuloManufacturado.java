package com.example.entitie;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "ArticuloManufacturado")
@PrimaryKeyJoinColumn(name = "id_control")
public class ArticuloManufacturado extends Control {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "denominacion")
	private String denominacion;
	@Column(name = "precio_venta")
	private double precioVenta;
	@Column(name = "tiempo_cocina")
	private int tiempoEstimadoCocina;
	@Column(name = "image")
	private String image;

	@ManyToOne()
	@JoinColumn(name = "id_rubro", referencedColumnName = "id_control")
	private RubroGeneral rubro;

	@OneToMany(mappedBy = "manufacturado", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<ArticuloManufacturadoDetalle> manufacturadoDetalle;

	public ArticuloManufacturado() {
	}

	public ArticuloManufacturado(String denominacion, double precioVenta, int tiempoEstimadoCocina, String image) {
		this.denominacion = denominacion;
		this.precioVenta = precioVenta;
		this.tiempoEstimadoCocina = tiempoEstimadoCocina;
		this.image = image;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public int getTiempoEstimadoCocina() {
		return tiempoEstimadoCocina;
	}

	public void setTiempoEstimadoCocina(int tiempoEstimadoCocina) {
		this.tiempoEstimadoCocina = tiempoEstimadoCocina;
	}

	public RubroGeneral getRubro() {
		return rubro;
	}

	public void setRubro(RubroGeneral rubro) {
		this.rubro = rubro;
	}

	public List<ArticuloManufacturadoDetalle> getManufacturadoDetalle() {
		return manufacturadoDetalle;
	}

	public void setManufacturadoDetalle(List<ArticuloManufacturadoDetalle> manufacturadoDetalle) {
		this.manufacturadoDetalle = manufacturadoDetalle;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
