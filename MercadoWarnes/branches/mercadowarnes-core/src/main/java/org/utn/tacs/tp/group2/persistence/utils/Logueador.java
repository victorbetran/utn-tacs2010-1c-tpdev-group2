package org.utn.tacs.tp.group2.persistence.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.utn.tacs.tp.group2.domain.pieza.Pedido;

public class Logueador {
	
	private static Logueador instancia;
		
	public static Logueador getInstancia()
	{
		if (instancia==null)
			instancia=new Logueador();
		return instancia;
	}
	
	private Logueador()
	{		
//		DOMConfigurator.configure("src/main/resources/log4j.cfg.xml");
	}
	
	public void loguearTransaccion(Pedido pedido)
	{
		Log logger = LogFactory.getLog(Pedido.class);		
		logger.info(pedido.getId().toString() + " , " + "Operacion: " + pedido.getEstado());	
	}
	
	public void loguearDebug(String mensaje)
	{
		Log logger = LogFactory.getLog("debug");		
		logger.debug(mensaje);	
	}
	
	
	
	
	
	
}
