package org.utn.tacs.tp.group2.persistence;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Abstraccion de un objeto persistente. Posee toda la l�gica para que un objeto
 * de dominio pueda ser persistido.
 */

@MappedSuperclass
public abstract class PersistentObject {

	//********************************************
	//** ATRIBUTTES
	//********************************************
	@Id
	protected Long id = UUIDGenerator.getInstance().getId();
	
	//********************************************
	//** ID GETTER & SETTERS
	//********************************************
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override public abstract boolean equals(Object obj);
	
	@Override public int hashCode(){
		return this.getId().hashCode();
	}

}
