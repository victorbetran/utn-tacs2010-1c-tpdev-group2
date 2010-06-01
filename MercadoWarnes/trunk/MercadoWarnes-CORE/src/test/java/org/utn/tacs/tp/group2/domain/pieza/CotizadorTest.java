package org.utn.tacs.tp.group2.domain.pieza;

import java.math.BigDecimal;
import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.utn.tacs.tp.group2.pieza.Cotizador;

public class CotizadorTest {
	
	private Cotizador cotizador;
	
	@Before
	public void setUp() {
		this.cotizador=Cotizador.getInstancia();
	}
	

	@Test
	public void ConvertirADolares()
	{			
		this.cotizador.setCotizacionDolaresAPesos(4);
		BigDecimal pesos=new BigDecimal(40);
		double dolares=this.cotizador.getEnDolares(pesos);
		Assert.assertEquals(dolares, 10.0);
		
	}
	
	@Test
	public void ConvertirAPesos()
	{			
		this.cotizador.setCotizacionDolaresAPesos(4);
		BigDecimal dolares=new BigDecimal(10);
		double pesos=this.cotizador.getEnPesos(dolares);
		Assert.assertEquals(pesos, 40.0);
		
	}
	
}
