package org.utn.tacs.tp.group2.dao;
/**
 * Administrador de IDs para los objetos de la aplicacion. 
 * Este objeto es momentaneo hasta que el modelo se integre a la capa
 * de persistencia. Luego habria que decidir qué política de generacion
 * y administracion de IDs se utilizara.
 */
public final class IDAsignator {

	//********************************************
	//** ATRIBUTTES
	//********************************************
	private static IDAsignator INSTANCE;
	private long seed;
	
	
	//********************************************
	//** PRIVATE CONSTRUCTOR
	//********************************************
	private IDAsignator() {
		this.seed = 0;
	}
	
	
	//********************************************
	//** PUBLIC CLASS METHODS
	//********************************************
	public static IDAsignator getInstance(){
		if(INSTANCE == null)
			INSTANCE = new IDAsignator();
		return INSTANCE;
	}
	
	
	//********************************************
	//** PUBLIC METHODS
	//********************************************
	public String getId(){
		return String.valueOf(++ this.seed);
	}
}
