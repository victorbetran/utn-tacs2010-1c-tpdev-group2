package org.utn.tacs.tp.group2.service;

import java.util.List;

import org.utn.tacs.tp.group2.daos.interfaces.PiezaDAO;
import org.utn.tacs.tp.group2.pieza.Auto;
import org.utn.tacs.tp.group2.pieza.Pieza;

/**
 * Interfaz del Servicio de Piezas.
 */
public interface PiezaService {

	public PiezaDAO getPiezaDAO();
	
	public Pieza newPieza();

	public void deletePieza(Pieza pieza);

	public Pieza loadPiezaById(Long id);

	public List<Pieza> loadPiezasByCategoria(String string);

	public List<Pieza> loadPiezasByAuto(Auto auto);

	public List<Pieza> loadPiezasReservadas();
}
