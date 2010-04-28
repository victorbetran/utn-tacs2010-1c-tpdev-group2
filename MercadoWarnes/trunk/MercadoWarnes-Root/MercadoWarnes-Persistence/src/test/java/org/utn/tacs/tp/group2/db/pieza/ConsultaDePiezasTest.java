package org.utn.tacs.tp.group2.db.pieza;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.utn.tacs.tp.group2.pieza.Pieza;

public class ConsultaDePiezasTest extends PiezaTest{

	Pieza piezaPersistida;
	Pieza piezaPersistidaA;
	Pieza piezaCategoriaPremiunA;
	Pieza piezaCategoriaPremiunB;
	
	@Override
	public void setUp() {
		super.setUp();
		
		this.piezaPersistida = new Pieza();
		this.dao.save(this.piezaPersistida);
		
		this.piezaPersistidaA = new Pieza();
		this.dao.save(this.piezaPersistidaA);
		
		this.piezaCategoriaPremiunA = new Pieza().setCategoria("PREMIUM");
		this.dao.save(this.piezaCategoriaPremiunA);
		
		this.piezaCategoriaPremiunB = new Pieza().setCategoria("PREMIUM");
		this.dao.save(this.piezaCategoriaPremiunB);
		
	}
	
	@Test
	public void consultarPiezaPorIDTest(){
		Pieza piezaObtenidoConDao = dao.findByID(piezaPersistida.getId());
		Assert.assertEquals("La Pieza persistida no coincide con la accedida.",piezaPersistida, piezaObtenidoConDao);
	}
	
	/**
	 * Consulta una pieza existente en la BD por su Codigo
	 */
	@Test 
	public void consultarUnaPiezaPorCodigo(){
		
	}
	
	/**
	 * Consulta las piezas Disponibles de una determinada Categoria.
	 */
	@Test 
	public void consultarPiezasDisponiblesPorCategoria(){
		List<Pieza> piezasPersistidasFromDao = this.dao.findByCategoria("PREMIUM");
		Assert.assertEquals(2, piezasPersistidasFromDao.size());
		Assert.assertTrue(piezasPersistidasFromDao.contains(piezaCategoriaPremiunA));
		Assert.assertTrue(piezasPersistidasFromDao.contains(piezaCategoriaPremiunB));
	}
	
//	
//	/**
//	 * Consulta una pieza pertenecientes a un determinado auto.
//	 */
//	@Test 
//	public void consultarPiezasPorAuto(){
//		//TODO: Implementar
//	}
//	
//	/**
//	 * Consulta las piezas reservadas.
//	 */
//	@Test 
//	public void consultarPiezasReservadas(){
//		//TODO: Implementar
//	}
//	
//	/**
//	 * Consulta las piezas vendidas de un auto.
//	 */
//	@Test 
//	public void consultarPiezasVendidasDeUnAuto(){
//		//TODO: Implementar
//	}
}
