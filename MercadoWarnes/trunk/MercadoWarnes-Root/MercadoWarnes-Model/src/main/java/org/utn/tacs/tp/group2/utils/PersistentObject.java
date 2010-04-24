package org.utn.tacs.tp.group2.utils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Abstracción de un objeto persistente. Posee toda la lógica para que un objeto
 * de dominio pueda ser persistido.
 * @see http://johannburkard.de/software/uuid/
 */
@Entity
public abstract class PersistentObject {

	//********************************************
	//** ATRIBUTTES
	//********************************************
	@Id
	@GeneratedValue
	protected Long id;

	//********************************************
	//** ID GETTER & SETTERS
	//********************************************
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}
	
	//********************************************
	//** ABSTRACT METHODS
	//********************************************
	@Override public abstract boolean equals(Object obj);
	
	@Override public abstract int hashCode();

	

}
