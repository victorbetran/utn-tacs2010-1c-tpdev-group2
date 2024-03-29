package org.utn.tacs.tp.group2.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.utn.tacs.tp.group2.pedido.Pedido;

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
		DOMConfigurator.configure("src/main/resources/log4j.cfg.xml");
	}
	
	public void loguearTransaccion(Pedido pedido)
	{
		Logger logger = Logger.getLogger(Pedido.class);		
		logger.info(pedido.getId().toString() + " , " + "Operacion: " + pedido.getEstado());	
	}
	
	public void loguearDebug(String mensaje)
	{
		Logger logger = Logger.getLogger("debug");		
		logger.debug(mensaje);	
	}
	
	
	
	
	
	
}
