package org.utn.tacs.tp.group2.daos.implementations;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.utn.tacs.tp.group2.daos.interfaces.DAOPiezaIf;
import org.utn.tacs.tp.group2.pieza.Pieza;

public class DAOPiezaImpl extends HibernateDaoSupport implements DAOPiezaIf {

	public void save(Pieza pieza) {
		getHibernateTemplate().save(pieza);
	}

	public void delete(Pieza pieza) {
		getHibernateTemplate().delete(pieza);
	}

	@SuppressWarnings("unchecked")
	public List<Pieza> loadAll() {
		return getHibernateTemplate().loadAll(Pieza.class);
	}

	public void deleteAll() {
		// COSAS QUE PROBE Y NO FUNCIONARON...
		// List<Pieza> piezas = getHibernateTemplate().find("FROM Pieza");
		// getHibernateTemplate().deleteAll(piezas);
		// getHibernateTemplate().flush();
		getHibernateTemplate().bulkUpdate("DELETE FROM Pieza");
	}
	
	public boolean isPersisted(Pieza pieza){
		
//		return getHibernateTemplate().contains(pieza);
		List<Pieza> piezas = loadAll();
		if(piezas == null){
			return false;
		} else {
			return piezas.contains(pieza);
		}		
	}

}
