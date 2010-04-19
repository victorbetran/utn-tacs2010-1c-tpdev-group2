package org.utn.tacs.tp.group2.daos;

import java.util.List;

import org.utn.tacs.tp.group2.pieza.Auto;
import org.utn.tacs.tp.group2.pieza.CategoriaPieza;
import org.utn.tacs.tp.group2.pieza.EstadoPieza;
import org.utn.tacs.tp.group2.pieza.Pieza;

public interface PiezaDAO {
	
	/**
	 * Devuelve la pieza cuyo ID corresponde al id pasado al mensaje.
	 */
	Pieza findByID(String id);
	
	/**
	 * Devuelve la pieza correspondiente al código.
	 */
	Pieza findByCodigo(String codigo);
	
	/**
	 * Devuelve un listado con las piezas que se encuentran en un determinado estado.
	 */
	List<Pieza> findByEstado(EstadoPieza estado);
	
	/**
	 * Devuelve un listado con las piezas que son de una determinada categoría.
	 */
	List<Pieza> findByCategoria(CategoriaPieza categoria);
	
	/**
	 * Devuelve un listado con las piezas pertenecientes a un determinado auto.
	 */
	List<Pieza> findByAuto(Auto auto);
	
	/**
	 * Devuelve un listado con las piezas que se encuentran en un estado y forman parte de una categoria.
	 */
	List<Pieza> findByEstadoAndCategoria(EstadoPieza estado, CategoriaPieza categoria);
	
	/**
	 * Devuelve un listado con las piezas que se encuentran en un estado y pertenecen a un auto
	 */
	List<Pieza> findByEstadoAndAuto(EstadoPieza estado, Auto auto);
	
	/**
	 * Guarda una pieza en la BD
	 */
	void save(Pieza pieza);

	
	
}
