package org.utn.tacs.tp.group2.pieza;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Precio {

	// ********************************************
	// ** ATRIBUTTES
	// ********************************************
	@Column
	private Moneda moneda;
	
	@Column
	private BigDecimal valor;
	
	
	// ********************************************
	// ** PUBLIC CONSTRUCTOR
	// ********************************************
	
	public Precio(Moneda moneda, BigDecimal valor) {		
		this.moneda = moneda;
		this.valor = valor;
	}
	
	
	public Precio(){}
	
	
	// ********************************************
	// ** GETTERS & SETTERS
	// ********************************************

	public BigDecimal getValor() {
		return valor;
	}

	

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}
}
