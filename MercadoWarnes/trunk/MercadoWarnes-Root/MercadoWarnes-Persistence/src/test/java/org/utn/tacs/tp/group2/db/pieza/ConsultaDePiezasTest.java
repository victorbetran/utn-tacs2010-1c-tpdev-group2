package org.utn.tacs.tp.group2.db.pieza;

import org.junit.Assert;
import org.junit.Test;
import org.utn.tacs.tp.group2.pieza.Pieza;

public class ConsultaDePiezasTest extends PiezaTest{

	Pieza piezaPersistida;
	Pieza piezaPersistidaA;
	
	@Override
	public void setUp() {
		super.setUp();
		
		piezaPersistida = new Pieza("");
		dao.save(piezaPersistida);
		
		piezaPersistidaA = new Pieza("");
		dao.save(piezaPersistidaA);
		
	}
	
	@Test
	public void consultarPiezaPorIDTest(){
		Pieza pedidoObtenidoConDao = dao.findByID(piezaPersistida.getId());
		Assert.assertEquals("La Pieza persistida no coincide con la accedida.",piezaPersistida, pedidoObtenidoConDao);
	}
	
}
