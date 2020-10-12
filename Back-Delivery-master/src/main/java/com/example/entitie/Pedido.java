package com.example.entitie;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Pedido")
public class Pedido implements Serializable {

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
	@Column(name = "estado")
	private int estado;
	@Column(name = "horaEstimadaFin")
	private Date horaEstimadaFin;
	@Column(name = "tipoEnvio")
	private int tipoEnvio;

	
	@ManyToOne()
	@JoinColumn(name = "id_cliente", referencedColumnName = "id")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<DetallePedido> detallePedido;
	
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "id_factura")
	private Factura factura;
	
	public Pedido() {
	}

	public Pedido(int id, Date fecha, int numero, int estado, Date horaEstimadaFin, int tipoEnvio) {
		this.id = id;
		this.fecha = fecha;
		this.numero = numero;
		this.estado = estado;
		this.horaEstimadaFin = horaEstimadaFin;
		this.tipoEnvio = tipoEnvio;
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

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getHoraEstimadaFin() {
		return horaEstimadaFin;
	}

	public void setHoraEstimadaFin(Date horaEstimadaFin) {
		this.horaEstimadaFin = horaEstimadaFin;
	}

	public int getTipoEnvio() {
		return tipoEnvio;
	}

	public void setTipoEnvio(int tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}

	
	public List<DetallePedido> getDetallePedido() {
		return detallePedido;
	}
	
	public void setDetallePedido(List<DetallePedido> detallePedido) {
		this.detallePedido = detallePedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	
}
