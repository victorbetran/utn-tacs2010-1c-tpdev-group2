package org.utn.tacs.tp.group2.daos.interfaces;

import java.util.List;

import org.utn.tacs.tp.group2.pieza.Pieza;

public interface DAOPiezaIf {

	public void save(Pieza pieza);

	public void delete(Pieza pieza);

	public List<Pieza> loadAll();

	public void deleteAll();
	
	public boolean isPersisted(Pieza pieza);
}
