package com.example.entitie;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Factura")
public class Factura implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "fecha")
	private Date fecha;

	@Column(name = "numero")
	private long numero;

	@Column(name = "montoDescuento")
	private double montoDescuento;

	@Column(name = "total")
	private double total;

	@Column(name = "formaPago")
	private String formaPago;

	@Column(name = "nroTarjeta")
	private String nroTarjeta;
	
	@OneToOne(mappedBy = "factura", fetch = FetchType.LAZY)
	private Pedido pedido;
	
	public Factura() {

	}

	public Factura(int id, Date fecha, int numero, double montoDescuento, double total, String formaPago,
			String nroTarjeta) {
		this.id = id;
		this.fecha = fecha;
		this.numero = numero;
		this.montoDescuento = montoDescuento;
		this.total = total;
		this.formaPago = formaPago;
		this.nroTarjeta = nroTarjeta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public double getMontoDescuento() {
		return montoDescuento;
	}

	public void setMontoDescuento(double montoDescuento) {
		this.montoDescuento = montoDescuento;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public String getNroTarjeta() {
		return nroTarjeta;
	}

	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

}
