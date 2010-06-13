package org.utn.tacs.tp.group2.service.definition;

import java.util.List;

import org.utn.tacs.tp.group2.pieza.Pieza;

/**
 * Interfaz del Servicio de Piezas.
 */
public interface PiezaService {

	public Pieza loadPiezaById(Long id);
	
	public Pieza getPiezaById(String id);

	public List<Pieza> getPiezasByCategoria(String categoria);

	public List<Pieza> getPiezasByAuto(String autoId);

	public List<Pieza> getPiezasVendidasByAuto(String autoId);
	
	public int getPorcentajePiezasVendidasByAuto(String autoId);

	public List<Pieza> getAllPiezas();

	public List<Pieza> getPiezasByEstado(String estado);	
	
}
