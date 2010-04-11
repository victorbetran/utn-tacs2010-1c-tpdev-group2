package org.utn.tacs.tp.group2.consultas;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.createNiceMock;
import static org.easymock.classextension.EasyMock.replay;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.utn.tacs.tp.group2.dao.DAOFactory;
import org.utn.tacs.tp.group2.dao.PiezaDAO;
import org.utn.tacs.tp.group2.exceptions.PiezaInexistenteException;
import org.utn.tacs.tp.group2.pieza.Auto;
import org.utn.tacs.tp.group2.pieza.CategoriaPieza;
import org.utn.tacs.tp.group2.pieza.EstadoPieza;
import org.utn.tacs.tp.group2.pieza.Pieza;

public class ConsultarPiezasTest {

	//********************************************
	//** ATRIBUTTES
	//********************************************
	private PiezaDAO dao;
	private Auto autoMockCon2Piezas;
	private Auto autoMockCon3Piezas;
	
	
	//********************************************
	//** CONSTANTES
	//********************************************
	private int CANTIDAD_PIEZAS_RESERVADAS = 3;
	private int CANTIDAD_PIEZAS_CLASSIC = 1;
	private int CANTIDAD_PIEZAS_STANDAR = 1;
	private int CANTIDAD_PIEZAS_GOLD = 3;
	private int CANTIDAD_PIEZAS_PREMIUM = 0;
	private int CANTIDAD_PIEZAS_AUTO_AAA123 = 2;
	private int CANTIDAD_PIEZAS_AUTO_ZZZ666 = 3;

	
	//********************************************
	//** SET UP
	//********************************************
	@Before 
	public void setUp() throws Exception {
		
		//Obtengo el DAO
		this.dao = DAOFactory.getPiezaDAO();
		
		//Creo una lista de 2 piezas
		List<Pieza> listadoDe2Piezas = new ArrayList<Pieza>();
		
		//Creo una lista de 2 piezas
		List<Pieza> listadoDe3Piezas = new ArrayList<Pieza>();
		
		//Creo 2 autos mock con diferentes cantidades de piezas
		this.autoMockCon2Piezas = createAutoMock("AAA123", listadoDe2Piezas);
		this.autoMockCon3Piezas = createAutoMock("ZZZ666", listadoDe3Piezas);
		
		//Creo las piezas - 3 Reservadas - 1 Disponible - 1 Vendida
		Pieza piezaMock1 = createPiezaMock("1111", "A-001", CategoriaPieza.getClassic(), this.autoMockCon2Piezas, EstadoPieza.getVendida());
		Pieza piezaMock2 = createPiezaMock("2222", "A-002", CategoriaPieza.getStandar(), this.autoMockCon2Piezas, EstadoPieza.getReservada());
		Pieza piezaMock3 = createPiezaMock("3333", "A-003", CategoriaPieza.getGold(), this.autoMockCon3Piezas, EstadoPieza.getDisponible());
		Pieza piezaMock4 = createPiezaMock("4444", "A-004", CategoriaPieza.getGold(), this.autoMockCon3Piezas, EstadoPieza.getReservada());
		Pieza piezaMock5 = createPiezaMock("5555", "A-005", CategoriaPieza.getGold(), this.autoMockCon3Piezas, EstadoPieza.getReservada());
		
		//Agrego las piezas al listado de piezas
		listadoDe2Piezas.add(piezaMock1);
		listadoDe2Piezas.add(piezaMock2);
		listadoDe3Piezas.add(piezaMock3);
		listadoDe3Piezas.add(piezaMock4);
		listadoDe3Piezas.add(piezaMock5);
		
		//Guardo las piezas en la BD
		this.dao.save(piezaMock1);
		this.dao.save(piezaMock2);
		this.dao.save(piezaMock3);
		this.dao.save(piezaMock4);
		this.dao.save(piezaMock5);
	}
	
	
	//********************************************
	//** TEST METHODS
	//********************************************
	/**
	 * Consulta una pieza existente en la BD por su ID
	 */
	@Test 
	public void consultarUnaPiezaPorID(){
		Pieza pieza = this.dao.findByID("4444");
		Assert.assertTrue(pieza.getId().equals("4444"));
	}
	
	/**
	 * Consulta una pieza que no existe en la BD por su ID
	 */
	@Test(expected=PiezaInexistenteException.class) 
	public void consultarUnaPiezaInexistentePorID(){
		this.dao.findByID("9");
	}
	
	/**
	 * Consulta una pieza existente en la BD por su Codigo
	 */
	@Test 
	public void consultarUnaPiezaPorCodigo(){
		Pieza pieza = this.dao.findByCodigo("A-001");
		Assert.assertTrue(pieza.getCodigo().equals("A-001"));
	}
	
	/**
	 * Consulta una pieza existente en la BD por su Categoria
	 */
	@Test 
	public void consultarPiezasPorCategoria(){
		List<Pieza> goldPiezas = this.dao.findByCategoria(CategoriaPieza.getGold());
		List<Pieza> classicPiezas = this.dao.findByCategoria(CategoriaPieza.getClassic());
		List<Pieza> standarPiezas = this.dao.findByCategoria(CategoriaPieza.getStandar());
		List<Pieza> premiumPiezas = this.dao.findByCategoria(CategoriaPieza.getPremium());
		
		Assert.assertTrue(goldPiezas.size() == this.CANTIDAD_PIEZAS_GOLD);
		Assert.assertTrue(classicPiezas.size() == this.CANTIDAD_PIEZAS_CLASSIC);
		Assert.assertTrue(standarPiezas.size() == this.CANTIDAD_PIEZAS_STANDAR);
		Assert.assertTrue(premiumPiezas.size() == this.CANTIDAD_PIEZAS_PREMIUM );
	}
	
	/**
	 * Consulta las piezas Disponibles de una determinada Categoria.
	 */
	@Test 
	public void consultarPiezasDisponiblesPorCategoria(){
		List<Pieza> piezasDisponiblesGold = this.dao.findByEstadoAndCategoria(EstadoPieza.getDisponible(), CategoriaPieza.getGold());
		List<Pieza> piezasDisponiblesClassic = this.dao.findByEstadoAndCategoria(EstadoPieza.getDisponible(), CategoriaPieza.getClassic());
		List<Pieza> piezasDisponiblesStandar = this.dao.findByEstadoAndCategoria(EstadoPieza.getDisponible(), CategoriaPieza.getStandar());
		List<Pieza> piezasDisponiblesPremium = this.dao.findByEstadoAndCategoria(EstadoPieza.getDisponible(), CategoriaPieza.getPremium());
		
		Assert.assertTrue(piezasDisponiblesGold.size() == 1);
		Assert.assertTrue(piezasDisponiblesClassic.size() == 0);
		Assert.assertTrue(piezasDisponiblesStandar.size() == 0);
		Assert.assertTrue(piezasDisponiblesPremium.size() == 0);
	}
	
	/**
	 * Consulta una pieza pertenecientes a un determinado auto.
	 */
	@Test 
	public void consultarPiezasPorAuto(){
		List<Pieza> piezasAutoAAA123 = this.dao.findByAuto(this.autoMockCon2Piezas);
		List<Pieza> piezasAutoZZZ666 = this.dao.findByAuto(this.autoMockCon3Piezas);
		Assert.assertTrue(piezasAutoAAA123.size() == this.CANTIDAD_PIEZAS_AUTO_AAA123);
		Assert.assertTrue(piezasAutoZZZ666.size() == this.CANTIDAD_PIEZAS_AUTO_ZZZ666 );
	}
	
	/**
	 * Consulta las piezas reservadas.
	 */
	@Test 
	public void consultarPiezasReservadas(){
		List<Pieza> piezasReservadas = this.dao.findByEstado(EstadoPieza.getReservada());
		Assert.assertTrue(piezasReservadas.size() == this.CANTIDAD_PIEZAS_RESERVADAS);
	}
	
	/**
	 * Consulta las piezas vendidas de un auto.
	 */
	@Test 
	public void consultarPiezasVendidasDeUnAuto(){
		List<Pieza> piezasVendidasDeAuto1 = this.dao.findByEstadoAndAuto(EstadoPieza.getVendida(), this.autoMockCon2Piezas);
		List<Pieza> piezasVendidasDeAuto2 = this.dao.findByEstadoAndAuto(EstadoPieza.getVendida(), this.autoMockCon3Piezas);
		
		Assert.assertTrue(piezasVendidasDeAuto1.size() == 1);
		Assert.assertTrue(piezasVendidasDeAuto2.size() == 0);
	}

	
	//********************************************
	//** PRIVATE METHODS
	//********************************************
	/**
	 * Crea un mock de pieza con los parametros especificados.
	 */
	private Pieza createPiezaMock(String id, String codigo, CategoriaPieza categoria, Auto auto, EstadoPieza estado){
		Pieza mock = createNiceMock(Pieza.class);
		expect(mock.getId()).andReturn(id).anyTimes();
		expect(mock.getCategoria()).andReturn(categoria).anyTimes();
		expect(mock.getCodigo()).andReturn(codigo).anyTimes();
		expect(mock.getAutoOrigen()).andReturn(auto).anyTimes();
		expect(mock.getEstado()).andReturn(estado).anyTimes();
		replay(mock);
		return mock;
	}
	
	/**
	 * Crea un mock de pieza con los parametros especificados.
	 */
	private Auto createAutoMock(String patente, List<Pieza> piezas){
		Auto mock = createMock(Auto.class);
		expect(mock.getPiezas()).andReturn(piezas).anyTimes();
		expect(mock.getPatente()).andReturn(patente).anyTimes();
		replay(mock);
		return mock;
	}
}
