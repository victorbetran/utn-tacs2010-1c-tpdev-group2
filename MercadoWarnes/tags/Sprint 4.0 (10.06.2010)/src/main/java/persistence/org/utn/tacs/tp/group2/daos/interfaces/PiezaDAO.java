package org.utn.tacs.tp.group2.daos.interfaces;

import java.util.List;

import org.utn.tacs.tp.group2.daos.implementations.AbstractDao;
import org.utn.tacs.tp.group2.daos.implementations.PiezaDAOImpl;
import org.utn.tacs.tp.group2.pieza.Auto;
import org.utn.tacs.tp.group2.pieza.EstadoPieza;
import org.utn.tacs.tp.group2.pieza.Pieza;


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

	public abstract List<Pieza> findByAutoId(Long autoId);

	public abstract List<Pieza> findByEstadoAndAutoId(EstadoPieza estado, Long autoId);

//	/**
//	 * Devuelve un listado con las piezas que se encuentran en un estado y forman parte de una categoria.
//	 */
//	public abstract List<Pieza> findByEstadoAndCategoria(String estado, String categoria);
	
//	/**
//	 * Devuelve un listado con las piezas que se encuentran en un estado y pertenecen a un auto
//	 */
//	public abstract List<Pieza> findByEstadoAndAuto(String estado, Auto auto);
	
}
