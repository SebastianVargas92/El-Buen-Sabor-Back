package com.example.entitie;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Rol")
public class Rol implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="descripcion")
	private String descripcion;
	
   //  @JsonIgnore
   //  @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL)
   //  @JsonBackReference
   //  private List<Cliente> clientes;

	
	public Rol() {
	}

	public Rol(int id, String rol) {
		this.id = id;
		this.descripcion = rol;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String rol) {
		this.descripcion = rol;
	}


	//public List<Cliente> getClientes() {
	//	return this.clientes;
	//}

	//public void setClientes(List<Cliente> clientes) {
	//	this.clientes = clientes;
	//}
	
	
}
