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
		
		this.piezaPersistida = new Pieza("");
		this.dao.save(this.piezaPersistida);
		
		this.piezaPersistidaA = new Pieza("");
		this.dao.save(this.piezaPersistidaA);
		
	}
	
	@Test
	public void consultarPiezaPorIDTest(){
		Pieza pedidoObtenidoConDao = dao.findByID(piezaPersistida.getId());
		Assert.assertEquals("La Pieza persistida no coincide con la accedida.",piezaPersistida, pedidoObtenidoConDao);
	}
	
	/**
	 * Consulta una pieza existente en la BD por su Codigo
	 */
	@Test 
	public void consultarUnaPiezaPorCodigo(){
		//TODO: Implementar
	}
	
	/**
	 * Consulta las piezas Disponibles de una determinada Categoria.
	 */
	@Test 
	public void consultarPiezasDisponiblesPorCategoria(){
		//TODO: Implementar
	}
	
	/**
	 * Consulta una pieza pertenecientes a un determinado auto.
	 */
	@Test 
	public void consultarPiezasPorAuto(){
		//TODO: Implementar
	}
	
	/**
	 * Consulta las piezas reservadas.
	 */
	@Test 
	public void consultarPiezasReservadas(){
		//TODO: Implementar
	}
	
	/**
	 * Consulta las piezas vendidas de un auto.
	 */
	@Test 
	public void consultarPiezasVendidasDeUnAuto(){
		//TODO: Implementar
	}
}
