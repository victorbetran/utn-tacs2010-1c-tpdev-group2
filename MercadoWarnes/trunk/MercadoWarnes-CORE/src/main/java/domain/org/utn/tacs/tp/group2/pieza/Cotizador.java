package org.utn.tacs.tp.group2.pieza;

import java.math.BigDecimal;

public class Cotizador {

	// ********************************************
	// ** ATRIBUTTES
	// ********************************************
	private static Cotizador instancia;
	private double dolaresAPesos=3.90;
	
	
	// ********************************************
	// ** PUBLIC METHODS
	// ********************************************
	
	public static Cotizador getInstancia()
	{
		if (instancia==null)
			instancia=new Cotizador();
		return instancia;
	}
	
		
	public double getEnDolares(BigDecimal pesos)
	{
		return pesos.longValue() / this.dolaresAPesos;
	}
	public double getEnPesos(BigDecimal dolares)
	{
		return dolares.longValue() * this.dolaresAPesos;
	}

	
	// ********************************************
	// ** GETTERS & SETTERS
	// ********************************************
	public double getDolaresAPesos() {
		return dolaresAPesos;
	}

	public void setDolaresAPesos(double dolaresAPesos) {
		this.dolaresAPesos = dolaresAPesos;
	}
	
	
	
}
