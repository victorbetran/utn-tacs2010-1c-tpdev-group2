package org.utn.tacs.tp.group2.persistence.pieza;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.utn.tacs.tp.group2.daos.interfaces.PiezaDAO;
import org.utn.tacs.tp.group2.pieza.Pieza;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:testingApplicationContext.xml"})
public class AbmDePiezasTest {

	private Pieza piezaPersistida;
	private Pieza piezaPersistidaFromDao;
	
	@Autowired()
	private PiezaDAO dao;
	
	
	@Before
	public void setUp() {
		this.piezaPersistida = new Pieza();
		this.dao.save(piezaPersistida);
		this.piezaPersistidaFromDao = this.dao.findByID(piezaPersistida.getId());
	}
	
	@Test
	@Transactional
	public void persistirPieza(){
		Assert.assertTrue(this.dao.isPersisted(piezaPersistida));
	}

	@Test
	@Transactional
	public void eliminarPiezaPersistidaTest(){
		this.dao.remove(piezaPersistida);
		Assert.assertFalse(this.dao.isPersisted(piezaPersistida));
	}
	
	@Test
	@Transactional()
	public void modificarPieza(){
		String descripcion = "Bulones MAX-TURBO";
		this.piezaPersistidaFromDao.setDescripcion(descripcion);
		
		Pieza piezaFromDao = this.dao.findByID(piezaPersistidaFromDao.getId());
		Assert.assertEquals(descripcion, piezaFromDao.getDescripcion());
	}

}
