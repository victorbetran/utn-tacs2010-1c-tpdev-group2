package org.utn.tacs.tp.group2.pieza;

import java.math.BigDecimal;

public class Cotizador {

	// ********************************************
	// ** ATRIBUTTES
	// ********************************************
	private static Cotizador instancia;
	private double cotizacionDolaresAPesos=3.90;
	
	
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
		return pesos.longValue() / this.cotizacionDolaresAPesos;
	}
	public double getEnPesos(BigDecimal dolares)
	{
		return dolares.longValue() * this.cotizacionDolaresAPesos;
	}

	
	// ********************************************
	// ** GETTERS & SETTERS
	// ********************************************
	public double getCotizacionDolaresAPesos() {
		return cotizacionDolaresAPesos;
	}

	public void setCotizacionDolaresAPesos(double dolaresAPesos) {
		this.cotizacionDolaresAPesos = dolaresAPesos;
	}
	
	
	
}
