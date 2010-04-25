package org.utn.tacs.tp.group2.daos.interfaces;

import java.util.List;

import org.utn.tacs.tp.group2.daos.implementations.AbstractDao;
import org.utn.tacs.tp.group2.daos.implementations.PiezaDAOImpl;
import org.utn.tacs.tp.group2.pieza.Auto;
import org.utn.tacs.tp.group2.pieza.CategoriaPieza;
import org.utn.tacs.tp.group2.pieza.EstadoPieza;
import org.utn.tacs.tp.group2.pieza.Pieza;


public abstract class PiezaDAO extends AbstractDao{

	private static PiezaDAO instance;
	public static PiezaDAO getInstance(){
		if(instance == null){
			instance = new PiezaDAOImpl();
		}
		
		return instance;
	}
	
	/**
	 * Devuelve la pieza cuyo ID corresponde al id pasado al mensaje.
	 */
	public abstract Pieza findByID(Long id);
	
	/**
	 * Devuelve la pieza correspondiente al código.
	 */
	public abstract Pieza findByCodigo(String codigo);
	
	/**
	 * Devuelve un listado con las piezas que se encuentran en un determinado estado.
	 */
	public abstract List<Pieza> findByEstado(EstadoPieza estado);
	
	/**
	 * Devuelve un listado con las piezas que son de una determinada categoría.
	 */
	public abstract List<Pieza> findByCategoria(CategoriaPieza categoria);
	
	/**
	 * Devuelve un listado con las piezas pertenecientes a un determinado auto.
	 */
	public abstract List<Pieza> findByAuto(Auto auto);
	
	/**
	 * Devuelve un listado con las piezas que se encuentran en un estado y forman parte de una categoria.
	 */
	public abstract List<Pieza> findByEstadoAndCategoria(EstadoPieza estado, CategoriaPieza categoria);
	
	/**
	 * Devuelve un listado con las piezas que se encuentran en un estado y pertenecen a un auto
	 */
	public abstract List<Pieza> findByEstadoAndAuto(EstadoPieza estado, Auto auto);
	
	/**
	 * Guarda una pieza en la BD
	 */
	public abstract void save(Pieza pieza);

	
	
}
