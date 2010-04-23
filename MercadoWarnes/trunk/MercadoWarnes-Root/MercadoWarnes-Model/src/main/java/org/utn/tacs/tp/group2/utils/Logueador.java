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
	
	public Logueador()
	{
		DOMConfigurator.configure("configLog.xml");
	}
	public void loguearTransaccion(Pedido pedido)
	{
		Logger logger = Logger.getLogger(Pedido.class);		
		logger.info(pedido.getId().toString() + " , " + "Operacion: " + pedido.getEstado());	
	}
	
	
	
	
	
	
}
