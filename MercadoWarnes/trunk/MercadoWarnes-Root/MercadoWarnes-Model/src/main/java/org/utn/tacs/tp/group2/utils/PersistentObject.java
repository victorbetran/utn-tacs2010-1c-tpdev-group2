package org.utn.tacs.tp.group2.utils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Abstracción de un objeto persistente. Posee toda la lógica para que un objeto
 * de dominio pueda ser persistido.
 * @see http://johannburkard.de/software/uuid/
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class PersistentObject {

	//********************************************
	//** ATRIBUTTES
	//********************************************
	@Id
	protected Long id = UUIDGenerator.getInstance().getId();
	
	public PersistentObject(){
		id = UUIDGenerator.getInstance().getId();
	}
	

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
	
	@Override public int hashCode(){
		return this.getId().hashCode();
	}

}
