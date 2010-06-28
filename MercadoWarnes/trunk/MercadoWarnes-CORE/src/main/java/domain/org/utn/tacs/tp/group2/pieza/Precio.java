package org.utn.tacs.tp.group2.pieza;

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
	
	
	// ********************************************
	// ** PUBLIC CONSTRUCTOR
	// ********************************************
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

	private double convertirValorAMoneda(Moneda monedaDestino) {		
		return this.valor * this.getCotizacionTo(monedaDestino);
	}
	
	/**
	 * Obtiene el multiplicador (cotizacion) para la moneda destino dependiendo de la moneda
	 * del precio actual.
	 */
	private double getCotizacionTo(Moneda monedaDestino){
		return Cotizador.getInstance().getCotizacionBetween(this.moneda, monedaDestino);
	}
}
