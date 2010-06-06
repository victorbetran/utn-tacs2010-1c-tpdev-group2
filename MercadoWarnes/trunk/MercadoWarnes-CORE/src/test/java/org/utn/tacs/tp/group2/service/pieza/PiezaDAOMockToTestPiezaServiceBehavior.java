package org.utn.tacs.tp.group2.service.pieza;

import java.util.ArrayList;
import java.util.List;

import org.utn.tacs.tp.group2.daos.interfaces.PiezaDAO;
import org.utn.tacs.tp.group2.pieza.Auto;
import org.utn.tacs.tp.group2.pieza.EstadoPieza;
import org.utn.tacs.tp.group2.pieza.Pieza;

public class PiezaDAOMockToTestPiezaServiceBehavior extends PiezaDAO{

	private List<Pieza> piezas = new ArrayList<Pieza>();
	
	@Override
	public void save(Pieza pieza) {
		this.piezas.add(pieza);
	}
	
	@Override
	public List<Pieza> findByAuto(Auto auto) {
		List<Pieza> toReturn = new ArrayList<Pieza>();
		
		for (Pieza pieza : this.piezas) {
			if(pieza.getAutoOrigen().equals(auto)){
				toReturn.add(pieza);
			}
		}
		
		return toReturn;
	}

	@Override
	public List<Pieza> findByCategoria(String categoria) {
		List<Pieza> toReturn = new ArrayList<Pieza>();
		
		for (Pieza pieza : this.piezas) {
			if(pieza.getCategoria().equals(categoria)){
				toReturn.add(pieza);
			}
		}
		
		return toReturn;
	}

	@Override
	public Pieza findByID(Long id) {
		for (Pieza pieza : this.piezas) {
			if(pieza.getId().equals(id)){
				return pieza;
			}
		}
		
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
