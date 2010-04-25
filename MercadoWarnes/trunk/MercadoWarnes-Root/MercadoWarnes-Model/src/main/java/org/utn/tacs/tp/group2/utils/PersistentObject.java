package org.utn.tacs.tp.group2.utils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Abstracción de un objeto persistente. Posee toda la lógica para que un objeto
 * de dominio pueda ser persistido.
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class PersistentObject {

	//********************************************
	//** ATRIBUTTES
	//********************************************
	protected Long id;
	
	//********************************************
	//** ID GETTER & SETTERS
	//********************************************
	@Id
	public Long getId() {
		if(this.id == null){
			this.id = UUIDGenerator.getInstance().getId();
		}
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
