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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "DetallePedido")
public class DetallePedido implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="cantidad")
	private int cantidad;
	
	@Column(name="subtotal")
	private double subtotal;
	
	@ManyToOne()
	@JoinColumn(name = "id_reventa", referencedColumnName = "id_control")
	private ArticuloReventa articuloReventa;
	
	@ManyToOne()
	@JoinColumn(name = "id_manufacturado", referencedColumnName = "id_control")
	private ArticuloManufacturado articuloManufacturado;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido") 
	@JsonBackReference 
	private Pedido pedido;
	
	/*
	 * La factura se agrega a cada detalle y se elimina la relacio con pedido
	@ManyToOne()
	@JoinColumn(name = "id_factura", referencedColumnName = "id")
	private Factura factura;
	
	*/
	
	public DetallePedido() {
		
	}

	public DetallePedido(int id, int cantidad, double subtotal) {
		this.id = id;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public ArticuloReventa getArticuloReventa() {
		return articuloReventa;
	}

	public void setArticuloReventa(ArticuloReventa articuloReventa) {
		this.articuloReventa = articuloReventa;
	}

	public ArticuloManufacturado getArticuloManufacturado() {
		return articuloManufacturado;
	}

	public void setArticuloManufacturado(ArticuloManufacturado articuloManufacturado) {
		this.articuloManufacturado = articuloManufacturado;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

}
