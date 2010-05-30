package org.utn.tacs.tp.group2.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.utn.tacs.tp.group2.pedido.Pedido;

public class TheLogger {
	
	/**
	 * Loguea el cambio de estado del pedido.
	 */
	public static void cambioDeEstadoLog(Pedido pedido){
		LoggerFactory.getLogger(pedido.getClass())
			.info("[Pedido:{}],[Operacion:{}]", pedido.getId(), pedido.getEstado());
	}
	
	/**
	 * Retorna el Logger para poder loguear desde cualquier clase del dominio
	 * de la aplicación.
	 */
	public static Logger getConsoleLogger(){
		return LoggerFactory.getLogger("org.utn.tacs.tp.group2");
	}
	
}
