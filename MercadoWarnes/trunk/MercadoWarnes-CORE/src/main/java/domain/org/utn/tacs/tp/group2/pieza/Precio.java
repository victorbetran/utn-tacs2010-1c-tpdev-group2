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
	private double valor;
	
	private static double cotizacionDolaresAPesos=3.90;
	
	
	// ********************************************
	// ** PUBLIC CONSTRUCTOR
	// ********************************************
	
	public static double getCotizacionDolaresAPesos() {
		return cotizacionDolaresAPesos;
	}


	public static void setCotizacionDolaresAPesos(double cotizacionDolaresAPesos) {
		Precio.cotizacionDolaresAPesos = cotizacionDolaresAPesos;
	}


	public Precio(Moneda moneda, double valor) {		
		this.moneda = moneda;
		this.valor = valor;
	}
	
	
	public Precio(){}
	
	
	// ********************************************
	// ** GETTERS & SETTERS
	// ********************************************

	public double getValor() {
		return valor;
	}

	

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}


	public double getValorEn(Moneda moneda) {
		if (this.moneda.equals(moneda))
			return this.getValor();
		else
			return this.convertirValorAMoneda(moneda);		
		
	}


	private double convertirValorAMoneda(Moneda moneda2) {		
		if (this.moneda.equals(Moneda.Pesos))
			return this.valor / cotizacionDolaresAPesos;
		else
			return this.valor * cotizacionDolaresAPesos;
	}
}
