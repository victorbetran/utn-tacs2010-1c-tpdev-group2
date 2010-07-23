package org.utn.tacs.tp.group2.service.pieza.precio;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.utn.tacs.tp.group2.pieza.Cotizador;
import org.utn.tacs.tp.group2.pieza.Moneda;

public class CotizadorTest {

	private Cotizador cotizador;
	
	@Before
	public void setUp() throws Exception {
		this.cotizador = Cotizador.getInstance();
		this.cotizador.loadCotizacionBetween(Moneda.PESO, Moneda.PESO, 1);
		this.cotizador.loadCotizacionBetween(Moneda.PESO, Moneda.DOLAR, 0.25641025641025641025641025641026);
		this.cotizador.loadCotizacionBetween(Moneda.DOLAR, Moneda.DOLAR, 1);
		this.cotizador.loadCotizacionBetween(Moneda.DOLAR, Moneda.PESO, 3.90);
	}
	
	@Test
	public void obtenerCotizacionDesdeArchivo(){
		Assert.assertTrue(this.cotizador.getCotizacionBetween(Moneda.PESO, Moneda.PESO) == 1);
		Assert.assertTrue(this.cotizador.getCotizacionBetween(Moneda.DOLAR, Moneda.DOLAR) == 1);
		Assert.assertTrue(this.cotizador.getCotizacionBetween(Moneda.DOLAR, Moneda.PESO) == 3.90);
		Assert.assertTrue(this.cotizador.getCotizacionBetween(Moneda.PESO, Moneda.DOLAR) == 0.25641025641025641025641025641026);
	}

}
