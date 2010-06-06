package org.utn.tacs.tp.group2.service.definition;

import java.util.List;

import org.utn.tacs.tp.group2.pieza.Pieza;

/**
 * Interfaz del Servicio de Piezas.
 */
public interface PiezaService {

//	public PiezaDAO getPiezaDAO();
//	
//	public Pieza newPieza();
//
//	public void delete(Pieza pieza);

	public Pieza getPiezaById(Long id);

	public List<Pieza> getPiezasByCategoria(String categoria);

	public List<Pieza> getPiezasByAuto(String autoId);

	public List<Pieza> getPiezasReservadas();

	public List<Pieza> getAllPiezas();	
	
}
