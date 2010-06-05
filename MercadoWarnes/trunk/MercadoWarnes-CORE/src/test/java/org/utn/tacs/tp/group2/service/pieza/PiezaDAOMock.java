package org.utn.tacs.tp.group2.service.pieza;

import java.util.ArrayList;
import java.util.List;

import org.utn.tacs.tp.group2.daos.interfaces.PiezaDAO;
import org.utn.tacs.tp.group2.pieza.Auto;
import org.utn.tacs.tp.group2.pieza.EstadoPieza;
import org.utn.tacs.tp.group2.pieza.Pieza;

public class PiezaDAOMock extends PiezaDAO{

	private List<Pieza> piezas = new ArrayList<Pieza>();
	
	@Override
	public void save(Pieza pieza) {
		this.piezas.add(pieza);
	}
	
	@Override
	public List<Pieza> findByAuto(Auto auto) {
		return null;
	}

	@Override
	public List<Pieza> findByCategoria(String categoria) {
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
	
	

}
