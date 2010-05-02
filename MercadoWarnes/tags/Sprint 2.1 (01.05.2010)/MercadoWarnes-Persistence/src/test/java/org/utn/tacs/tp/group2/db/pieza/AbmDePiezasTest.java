package org.utn.tacs.tp.group2.db.pieza;

import org.junit.Assert;
import org.junit.Test;
import org.utn.tacs.tp.group2.pieza.Pieza;


public class AbmDePiezasTest extends PiezaTest {

	private Pieza piezaPersistida;
	private Pieza piezaPersistidaFromDao;

	@Override
	public void setUp() {
		super.setUp();
		
		this.piezaPersistida = new Pieza();
		this.dao.save(piezaPersistida);
		this.piezaPersistidaFromDao = this.dao.findByID(piezaPersistida.getId());
		
	}
	
	@Test
	public void persistirPieza(){
		Assert.assertTrue(this.dao.isPersisted(piezaPersistida));
	}

	@Test
	public void eliminarPiezaPersistidaTest(){
		this.dao.remove(piezaPersistida);
		Assert.assertFalse(this.dao.isPersisted(piezaPersistida));
	}
	
	@Test
	public void modificarPieza(){
		String descripcion = "Bulones MAX-TURBO";
		this.piezaPersistidaFromDao.setDescripcion(descripcion);
		
		Pieza piezaFromDao = this.dao.findByID(piezaPersistidaFromDao.getId());
		Assert.assertEquals(descripcion, piezaFromDao.getDescripcion());
	}

}
