package org.utn.tacs.tp.group2.service;

import org.utn.tacs.tp.group2.daos.interfaces.PiezaDAO;
import org.utn.tacs.tp.group2.pieza.Pieza;

/**
 * Implementación del Servicio de Piezas.
 */
public class PiezaServiceImpl implements PiezaService {

	private PiezaDAO piezaDAO;

	public Pieza newPieza() {
		Pieza pieza = new Pieza();
		piezaDAO.save(pieza);
		return pieza;
	}

	public void deletePieza(Pieza pieza) {
		piezaDAO.remove(pieza);
	}
	
	public Pieza loadPiezaById(Long id) {
		return piezaDAO.findByID(id);
	}

	// ********************************************
	// ** GETTERS & SETTERS
	// ********************************************
	public void setPiezaDAO(PiezaDAO piezaDAO) {
		this.piezaDAO = piezaDAO;
	}

	public PiezaDAO getPiezaDAO() {
		return piezaDAO;
	}


}
