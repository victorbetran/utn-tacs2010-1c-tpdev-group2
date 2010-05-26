package org.utn.tacs.tp.group2.persistence.pieza;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.utn.tacs.tp.group2.daos.implementations.AbstractDao;
import org.utn.tacs.tp.group2.daos.interfaces.PiezaDAO;
import org.utn.tacs.tp.group2.persistence.utils.DataBaseHandler;
import org.utn.tacs.tp.group2.pieza.Pieza;

public abstract class PiezaTest extends DataBaseHandler<Pieza>{

	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	protected PiezaDAO dao = (PiezaDAO) applicationContext.getBean("piezaDAO");
	
	@Override
	protected AbstractDao<Pieza> getDao() {
		return dao;
	}
	
}
