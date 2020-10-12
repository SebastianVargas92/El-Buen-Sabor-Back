package com.example.entitie;

public class PlatoVendido {
	
	
	private ArticuloManufacturado articulo;
	
	private int cantidad;

	public ArticuloManufacturado getArticulo() {
		return articulo;
	}
	
	public PlatoVendido() {
		
	}
	
	

	public void setArticulo(ArticuloManufacturado articulo) {
		this.articulo = articulo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
