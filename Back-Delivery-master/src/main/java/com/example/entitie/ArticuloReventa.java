package com.example.entitie;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="articulo_reventa")
@PrimaryKeyJoinColumn(name = "id_control")
public class ArticuloReventa extends Control {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="denominacion")
	private String denominacion;
	@Column(name="precio_compra")
	private double precioCompra;
	@Column(name="precio_venta")
	private double precioVenta;
	@Column(name="stock_actual")
	private double stockActual;
	@Column(name="stock_minimo")
	private double stockMinimo;
	@Column(name="imagen")
	private String imagen;
	
	@ManyToOne()
	@JoinColumn(name = "id_unidad_medida", referencedColumnName = "id")
	private UnidadMedida unidadMedida;
	
	@ManyToOne()
    @JoinColumn(name = "id_rubro",referencedColumnName="id_control") 
	private RubroArticulo rubroArticulo;
	
	
	public ArticuloReventa() {
	}

	public ArticuloReventa(String denominacion, double precioCompra, double precioVenta,
			double stockActual, double stockMinimo, String imagen) {
		this.denominacion = denominacion;
		this.precioCompra = precioCompra;
		this.precioVenta = precioVenta;
		this.stockActual = stockActual;
		this.stockMinimo = stockMinimo;
		this.imagen = imagen;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public double getStockActual() {
		return stockActual;
	}

	public void setStockActual(double stockActual) {
		this.stockActual = stockActual;
	}

	public double getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(double stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public RubroArticulo getRubroArticulo() {
		return rubroArticulo;
	}

	public void setRubroArticulo(RubroArticulo rubroArticulo) {
		this.rubroArticulo = rubroArticulo;
	}

	public UnidadMedida getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(UnidadMedida unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	
}
