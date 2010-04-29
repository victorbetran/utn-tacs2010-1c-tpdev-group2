package org.utn.tacs.tp.group2.db.pieza;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.utn.tacs.tp.group2.pieza.Auto;
import org.utn.tacs.tp.group2.pieza.EstadoPieza;
import org.utn.tacs.tp.group2.pieza.EstadoPiezaReservada;
import org.utn.tacs.tp.group2.pieza.EstadoPiezaVendida;
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
		Pieza pieza = new Pieza().setCodigo("COD1");
		this.dao.save(pieza);
		Assert.assertEquals(pieza.getId(),dao.findByCodigo("COD1").getId());
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
	
	
	/**
	 * Consulta una pieza pertenecientes a un determinado auto.
	 */
	@Test 
	public void consultarPiezasPorAuto(){
		Auto auto1=new Auto();		
		Pieza pieza1=new Pieza("COD1");
		pieza1.setAutoOrigen(auto1);		
		Pieza pieza2=new Pieza("COD2");
		pieza2.setAutoOrigen(auto1);		
		List<Pieza> piezas=this.dao.findByAuto(auto1);
		Assert.assertEquals(2, piezas.size());
	}
	
	/**
	 * Consulta las piezas reservadas.
	 */
	@Test 
	public void consultarPiezasReservadas(){		
		Pieza pieza1 = new Pieza("COD1").reservar();
		Pieza pieza2 = new Pieza("COD2").reservar();		
		Pieza pieza3 = new Pieza("COD4");
		this.dao.save(pieza1);
		this.dao.save(pieza2);
		this.dao.save(pieza3);		
		List<Pieza> piezas=this.dao.findByEstado("RESERVADA");
		Assert.assertEquals(2, piezas.size());	
		
	}
//	
	/**
	 * Consulta las piezas vendidas de un auto.
	 */
	@Test 
	public void consultarPiezasVendidasDeUnAuto(){
		Auto auto1=new Auto();		
		Pieza pieza1=new Pieza("COD1").reservar();
		pieza1.setAutoOrigen(auto1);
		pieza1.vender();
		Pieza pieza2=new Pieza("COD2").reservar();
		pieza2.setAutoOrigen(auto1);
		pieza2.vender();
		List<Pieza> piezas=this.dao.findByEstadoAndAuto("VENDIDA", auto1);
		Assert.assertEquals(2, piezas.size());
		
		
		
		
		
	}
}
