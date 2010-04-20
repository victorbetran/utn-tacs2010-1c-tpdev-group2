package org.utn.tacs.tp.group2.utils;

import javax.persistence.Id;

import com.eaio.uuid.UUID;

/**
 * Abstracción de un objeto persistente. Posee toda la lógica para que un objeto
 * de dominio pueda ser persistido.
 */
public abstract class PersistentObject {

	//********************************************
	//** ATRIBUTTES
	//********************************************
	@Id
	protected UUID id;


	//********************************************
	//** PROTECTED CONSTRUCTOR
	//********************************************
	protected PersistentObject() {
		this.id = new UUID();
	}
	
	
	//********************************************
	//** ID GETTER & SETTERS
	//********************************************
	/**
	 * Devuelve el id del objeto.
	 */
	public UUID getId() { return this.id; }

	/**
	 * Setea el id del objeto.
	 */
	public void setId(UUID id) {
		this.id = id;
	}
	
	
	//********************************************
	//** ABSTRACT METHODS
	//********************************************
	@Override public abstract boolean equals(Object obj);
	
	@Override public abstract int hashCode();
}
