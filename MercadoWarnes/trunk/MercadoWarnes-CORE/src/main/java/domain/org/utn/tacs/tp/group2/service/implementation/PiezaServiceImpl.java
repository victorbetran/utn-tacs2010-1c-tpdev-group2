package org.utn.tacs.tp.group2.service.implementation;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.utn.tacs.tp.group2.daos.interfaces.PiezaDAO;
import org.utn.tacs.tp.group2.pieza.Auto;
import org.utn.tacs.tp.group2.pieza.EstadoPieza;
import org.utn.tacs.tp.group2.pieza.Pieza;
import org.utn.tacs.tp.group2.service.definition.PiezaService;

/**
 * Implementaci�n del Servicio de Piezas.
 */
@Transactional
public class PiezaServiceImpl implements PiezaService {

	private PiezaDAO piezaDAO;

	public Pieza newPieza() {
		Pieza pieza = new Pieza();
		piezaDAO.save(pieza);
		return pieza;
	}

	public void delete(Pieza pieza) {
		piezaDAO.remove(pieza);
	}

	public Pieza getPiezaById(Long id) {
		return piezaDAO.findByID(id);
	}

	public List<Pieza> loadPiezasByCategoria(String categoria) {
		return piezaDAO.findByCategoria(categoria);
	}

	public List<Pieza> loadPiezasByAuto(Auto auto) {
		return piezaDAO.findByAuto(auto);
	}

	public List<Pieza> loadPiezasReservadas() {
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
