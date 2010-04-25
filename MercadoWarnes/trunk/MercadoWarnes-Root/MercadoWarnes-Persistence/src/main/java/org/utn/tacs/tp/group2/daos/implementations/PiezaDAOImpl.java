package org.utn.tacs.tp.group2.daos.implementations;

import java.util.List;

import org.utn.tacs.tp.group2.daos.interfaces.PiezaDAO;
import org.utn.tacs.tp.group2.pieza.Auto;
import org.utn.tacs.tp.group2.pieza.CategoriaPieza;
import org.utn.tacs.tp.group2.pieza.EstadoPieza;
import org.utn.tacs.tp.group2.pieza.Pieza;


public class PiezaDAOImpl extends PiezaDAO{

	@Override
	public List<Pieza> findByAuto(Auto auto) {
		return null;
	}

	@Override
	public List<Pieza> findByCategoria(CategoriaPieza categoria) {
		return null;
	}

	@Override
	public Pieza findByCodigo(String codigo) {
		return null;
	}

	@Override
	public List<Pieza> findByEstado(EstadoPieza estado) {
		return null;
	}

	@Override
	public List<Pieza> findByEstadoAndAuto(EstadoPieza estado, Auto auto) {
		return null;
	}

	@Override
	public List<Pieza> findByEstadoAndCategoria(EstadoPieza estado,
			CategoriaPieza categoria) {
		return null;
	}
	
	@Override
	public Pieza findByID(Long id) {
		return null;
	}

	@Override
	public void save(Pieza pieza) {
		
	}

}
