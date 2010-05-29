package org.utn.tacs.tp.group2.service;

import org.utn.tacs.tp.group2.pieza.Pieza;

/**
 * Interfaz del Servicio de Piezas.
 */
public interface PiezaService {

	public Pieza newPieza();

	public void deletePieza(Pieza pieza);
}
