package org.utn.tacs.tp.group2.service;

import org.utn.tacs.tp.group2.daos.interfaces.DAOPiezaIf;
import org.utn.tacs.tp.group2.pieza.Pieza;

/**
 * Implementación del Servicio de Piezas.
 */
public class PiezaServiceImpl implements PiezaService {

	private DAOPiezaIf piezaDAO;

	public Pieza newPieza() {
		Pieza pieza = new Pieza();
		piezaDAO.save(pieza);
		return pieza;
	}

	public void deletePieza(Pieza pieza) {
		piezaDAO.delete(pieza);
	}

	// ********************************************
	// ** GETTERS & SETTERS
	// ********************************************
	public void setPiezaDAO(DAOPiezaIf piezaDAO) {
		this.piezaDAO = piezaDAO;
	}

	public DAOPiezaIf getPiezaDAO() {
		return piezaDAO;
	}

}
