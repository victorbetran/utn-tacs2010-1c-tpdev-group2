package org.utn.tacs.tp.group2.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.utn.tacs.tp.group2.daos.exceptions.PiezaInexistenteException;
import org.utn.tacs.tp.group2.daos.interfaces.PiezaDAO;
import org.utn.tacs.tp.group2.pieza.EstadoPieza;
import org.utn.tacs.tp.group2.pieza.Pieza;
import org.utn.tacs.tp.group2.service.definition.PiezaService;

/**
 * Implementaci�n del Servicio de Piezas.
 */

public class PiezaServiceImpl implements PiezaService{

	@Autowired
	private PiezaDAO piezaDAO;

	public Pieza getPiezaById(Long id) {
		try{
			return piezaDAO.findByID(id);
		}catch (PiezaInexistenteException e) {
			return null;
		}
	}

	public List<Pieza> getAllPiezas() {
		return this.piezaDAO.findAll();
	}
	
	public List<Pieza> getPiezasByAuto(String autoId) {
		return piezaDAO.findByAutoId(Long.valueOf(autoId));
	}
	
	public List<Pieza> getPiezasByCategoria(String categoria) {
		return piezaDAO.findByCategoria(categoria);
	}
	
	public List<Pieza> getPiezasReservadas() {
		return piezaDAO.findByEstado(EstadoPieza.getEstadoReservada());
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
