package org.utn.tacs.tp.group2.utils;
/**
 * Administrador de IDs para los objetos de la aplicacion. 
 * Este objeto es momentaneo hasta que el modelo se integre a la capa
 * de persistencia. Luego habria que decidir qué política de generacion
 * y administracion de IDs se utilizara.
 */
public final class IDGenerator {

	//********************************************
	//** ATRIBUTTES
	//********************************************
	private static IDGenerator INSTANCE;
	private long seed;
	
	
	//********************************************
	//** PRIVATE CONSTRUCTOR
	//********************************************
	private IDGenerator() {
		this.seed = 0;
	}
	
	
	//********************************************
	//** PUBLIC CLASS METHODS
	//********************************************
	public static IDGenerator getInstance(){
		if(INSTANCE == null)
			INSTANCE = new IDGenerator();
		return INSTANCE;
	}
	
	
	//********************************************
	//** PUBLIC METHODS
	//********************************************
	public String getId(){
		return String.valueOf(++ this.seed);
	}
}
