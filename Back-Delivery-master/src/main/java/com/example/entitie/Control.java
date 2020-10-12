package com.example.entitie;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity()
@Table(name = "Control")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Control implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "fecha_alta")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaAlta;

	@Column(name = "fecha_baja")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaBaja;

	@Column(name = "fecha_modificacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaModificacion;

	@Column(name = "usuario_carga")
	private String usuarioCarga;

	@Column(name = "usuario_modificacion")
	private String usuarioModificacion;

	@Column(name = "usuario_baja")
	private String usuarioBaja;

	public Control() {

	}

	public Control(int id, Date fechaAlta, Date fehaBaja, Date fechaModificacion, String usuarioCarga,
			String usuarioModificacion, String usuarioBaja) {
		this.id = id;
		this.fechaAlta = fechaAlta;
		this.fechaBaja = fehaBaja;
		this.fechaModificacion = fechaModificacion;
		this.usuarioCarga = usuarioCarga;
		this.usuarioModificacion = usuarioModificacion;
		this.usuarioBaja = usuarioBaja;
	}

	

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fehaBaja) {
		this.fechaBaja = fehaBaja;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getUsuarioCarga() {
		return usuarioCarga;
	}

	public void setUsuarioCarga(String usuarioCarga) {
		this.usuarioCarga = usuarioCarga;
	}

	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public String getUsuarioBaja() {
		return usuarioBaja;
	}

	public void setUsuarioBaja(String usuarioBaja) {
		this.usuarioBaja = usuarioBaja;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
