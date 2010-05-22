package org.utn.tacs.tp.core.daos;

import java.util.List;

import org.utn.tacs.tp.core.domain.Auto;
import org.utn.tacs.tp.core.domain.EstadoPieza;
import org.utn.tacs.tp.core.domain.Pieza;


public abstract class PiezaDAO extends AbstractDao<Pieza>{

	private static PiezaDAO instance;
	public static PiezaDAO getInstance(){
		if(instance == null){
			instance = new PiezaDAOImpl();
		}
		
		return instance;
	}
	
	@Override
	protected Class<Pieza> getGenericClass() {
		return Pieza.class;
	}
	
	// *************************************************************
	// * DAO INTERFACE
	// *************************************************************
	
	/**
	 * Devuelve la pieza correspondiente al c�digo.
	 */
	public abstract Pieza findByCodigo(String codigo);
	
	/**
	 * Devuelve un listado con las piezas que se encuentran en un determinado estado.
	 */
	public abstract List<Pieza> findByEstado(EstadoPieza estado);
	
	/**
	 * Devuelve un listado con las piezas que son de una determinada categor�a.
	 */
	public abstract List<Pieza> findByCategoria(String categoria);
	
	/**
	 * Devuelve un listado con las piezas pertenecientes a un determinado auto.
	 */
	public abstract List<Pieza> findByAuto(Auto auto);
	
//	/**
//	 * Devuelve un listado con las piezas que se encuentran en un estado y forman parte de una categoria.
//	 */
//	public abstract List<Pieza> findByEstadoAndCategoria(String estado, String categoria);
	
//	/**
//	 * Devuelve un listado con las piezas que se encuentran en un estado y pertenecen a un auto
//	 */
//	public abstract List<Pieza> findByEstadoAndAuto(String estado, Auto auto);
	
}
