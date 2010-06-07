package org.utn.tacs.tp.group2.persistence.pieza;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.utn.tacs.tp.group2.daos.exceptions.PiezaInexistenteException;
import org.utn.tacs.tp.group2.daos.interfaces.PiezaDAO;
import org.utn.tacs.tp.group2.pieza.Auto;
import org.utn.tacs.tp.group2.pieza.EstadoPieza;
import org.utn.tacs.tp.group2.pieza.Moneda;
import org.utn.tacs.tp.group2.pieza.Pieza;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class ConsultaDePiezasTest {

	private Pieza piezaPersistida1;
	private Pieza piezaPersistida2;
	
	private Auto auto1;
	private Auto auto2;

	@Autowired()
	private PiezaDAO dao;
	
	@Before
	public void setUp() {

		this.piezaPersistida1 = new Pieza("PIEZA1",40,Moneda.Pesos);
		this.dao.save(this.piezaPersistida1);

		this.piezaPersistida2 = new Pieza("PIEZA2",40,Moneda.Pesos);
		this.dao.save(this.piezaPersistida2);

		auto1 = Auto.createAuto("EXP-074", "AK-47", 2009, new Date());
		auto2 = Auto.createAuto("FAST", "BMW-001", 2001, new Date());
	}

	/**
	 * Consulta una pieza existente en la BD por su ID
	 */
	@Test
	@Transactional
	public void consultarPiezaPorIDTest() {
		Pieza piezaObtenidoConDao = dao.findByID(piezaPersistida1.getId());
		Assert.assertEquals("La Pieza persistida no coincide con la accedida.", piezaPersistida1,piezaObtenidoConDao);
	}

	/**
	 * Consultar por un ID inexistente
	 */
	@Transactional
	@Test(expected = PiezaInexistenteException.class)
	public void consultarPiezaInexistenteTest() {
		dao.findByID(new Pieza("PIEZA INEXISTENTE",40,Moneda.Pesos).getId());
	}
	
	/**
	 * Consulta una pieza existente en la BD por su Codigo
	 */
	@Test
	@Transactional
	public void consultarUnaPiezaPorCodigo() {
		Assert.assertEquals("La Pieza persistida no coincide con la accedida.", piezaPersistida1.getId(), dao.findByCodigo("PIEZA1").getId());
	}

	/**
	 * Consulta piezas segun una categoria.
	 */
	@Test
	@Transactional
	public void consultarPiezasPorCategoria() {
		this.piezaPersistida1.setCategoria("PREMIUM");
		this.piezaPersistida2.setCategoria("PREMIUM");
		
		assertList(this.dao.findByCategoria("PREMIUM"), this.piezaPersistida1,this.piezaPersistida2);
	}
	
	/**
	 * Consulta una pieza segun el auto a la que pertenece.
	 */
	@Test
	@Transactional
	public void consultarPiezaPorAuto(){
		this.piezaPersistida1.setAutoOrigen(auto1);
		this.piezaPersistida2.setAutoOrigen(auto2);
		
		assertList(this.dao.findByAuto(auto1), this.piezaPersistida1);
	}

	/**
	 * Consulta un conjunto de piezas segun el auto a la que pertenecen.
	 */
	@Test
	@Transactional
	public void consultarPiezasPorAuto(){
		this.piezaPersistida1.setAutoOrigen(auto1);
		this.piezaPersistida2.setAutoOrigen(auto1);
		
		assertList(this.dao.findByAuto(auto1), this.piezaPersistida1, this.piezaPersistida2);
	}

	/**
	 * Consulta un conjunto de piezas segun el id del auto al que pertenecen.
	 */
	@Test
	@Transactional
	public void consultarPiezasPorIdDeAuto(){
		this.piezaPersistida1.setAutoOrigen(auto1);
		this.piezaPersistida2.setAutoOrigen(auto1);
		
		assertList(this.dao.findByAutoId(auto1.getId()), this.piezaPersistida1, this.piezaPersistida2);
	}
	
	/**
	 * Consulta las piezas disponibles.
	 */
	@Test
	@Transactional
	public void consultarPiezasDisponibles() {
		assertList(this.dao.findByEstado(EstadoPieza.getEstadoDisponible()), this.piezaPersistida1, this.piezaPersistida2);
	}
	
	/**
	 * Consulta las piezas reservadas.
	 */
	@Test
	@Transactional
	public void consultarPiezasReservadas() {
		this.piezaPersistida1.reservar();
		this.piezaPersistida2.reservar();
		
		assertList(this.dao.findByEstado(EstadoPieza.getEstadoReservada()), this.piezaPersistida1, this.piezaPersistida2);
	}

	/**
	 * Consulta las piezas vendidas.
	 */
	@Test
	@Transactional
	public void consultarPiezasVendidas() {
		this.piezaPersistida1.reservar();
		this.piezaPersistida1.vender();
		assertList(this.dao.findByEstado(EstadoPieza.getEstadoVendida()), this.piezaPersistida1);
	}
	
	// *******************************************************************************
	// *** HELPER
	// *******************************************************************************
	
	private void assertList(List<Pieza> toBeChecked,Pieza ...expected) {
		int qty = expected.length;
		
		Assert.assertEquals("La cantidad de elementos en la lista no es la esperada.",qty, toBeChecked.size());
		for (Pieza piezaExpected : expected) {
			Assert.assertTrue("Uno de los elemenos esperados no se encuentra en el conjunto.",toBeChecked.contains(piezaExpected));
		}
	}

}
