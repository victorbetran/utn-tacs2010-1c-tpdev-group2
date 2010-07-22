package ar.edu.utn.frba.tacs.warnes;

import java.io.Serializable;

public class Item implements Serializable{
	private static final long serialVersionUID = -1370405263872773214L;
	private String codigo;
	private Double precioUnitario;
	private Integer cantidad;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double calcularTotal() {
		return this.cantidad * precioUnitario;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
}
