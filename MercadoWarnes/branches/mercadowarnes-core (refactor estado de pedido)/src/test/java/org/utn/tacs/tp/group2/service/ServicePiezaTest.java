package org.utn.tacs.tp.group2.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.utn.tacs.tp.group2.pieza.Pieza;



public class ServicePiezaTest {
	
	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	private PiezaService piezaService = (PiezaService) applicationContext.getBean("piezaService");

	@Test
	public void crearPieza(){
		
//		Pieza p = piezaService.crearPieza();
//		
//		Assert.assertNotNull(p);
		
	}

}
