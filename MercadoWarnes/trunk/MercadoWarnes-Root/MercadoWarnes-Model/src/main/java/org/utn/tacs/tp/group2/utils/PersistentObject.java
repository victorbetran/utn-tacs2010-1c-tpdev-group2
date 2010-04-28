package org.utn.tacs.tp.group2.utils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Abstracci�n de un objeto persistente. Posee toda la l�gica para que un objeto
 * de dominio pueda ser persistido.
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class PersistentObject {

	//********************************************
	//** ATRIBUTTES
	//********************************************
//	@GeneratedValue(strategy=GenerationType.TABLE)
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
	
//		if (obj == null) {
//			return false;
//		}
//		else if (!this.getClass().isInstance(obj)) {
//			return false;
//		}
//		else if (obj == this) {
//			return true;
//		}else {
//			return this.getId().equals(((PersistentObject) obj).getId());
//		}
	
	@Override public int hashCode(){
		return this.getId().hashCode();
	}

}
