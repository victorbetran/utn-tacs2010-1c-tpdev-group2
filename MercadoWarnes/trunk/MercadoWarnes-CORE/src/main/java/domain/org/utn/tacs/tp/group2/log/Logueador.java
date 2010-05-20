package org.utn.tacs.tp.group2.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.utn.tacs.tp.group2.pedido.Pedido;

public class Logueador {
	
	//**************************************
	//** ATRIBUTTES
	//**************************************
	private static Logueador INSTANCE;
	private Logger logger = LoggerFactory.getLogger(Pedido.class);
		
	
	//**************************************
	//** CONSTRUCTION
	//**************************************
	public static Logueador getInstance()
	{
		if (INSTANCE == null)
			INSTANCE = new Logueador();
		return INSTANCE;
	}
	
	private Logueador(){}
	
	
	//**************************************
	//** INTERFACE
	//**************************************
	/**
	 * Permite loguear las transacciones sobre un pedido. 
	 */
	public void logTransaction(Pedido pedido)
	{
		this.logger.info("Pedido: {} => Operacion: {}", pedido.getId(), pedido.getEstado());
	}
	
	public void loguearDebug(String mensaje)
	{
		Logger logger = LoggerFactory.getLogger("DEBUG");		
		logger.debug(mensaje);	
	}
	
	
	
	
	
	
}
