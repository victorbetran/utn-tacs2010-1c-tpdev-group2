package org.utn.tacs.tp.group2.dao;

import java.util.List;

import org.utn.tacs.tp.group2.pieza.CategoriaPieza;
import org.utn.tacs.tp.group2.pieza.EstadoPieza;
import org.utn.tacs.tp.group2.pieza.Pieza;


public class PiezaDao {

	/**
	 * Devuelve un listado con las piezas que se encuentran en un determinado estado.
	 */
	public List<Pieza> findByEstado(EstadoPieza estado){
		return null;
	}
	
	/**
	 * Devuelve un listado con las piezas que son de una determinada categoría.
	 */
	public List<Pieza> findByCategoria(CategoriaPieza categoria){
		return null;
	}
	
	/**
	 * Devuelve la pieza correspondiente al código.
	 */
	public Pieza findByCodigo(String codigo){
		return null;
	}
	
}
