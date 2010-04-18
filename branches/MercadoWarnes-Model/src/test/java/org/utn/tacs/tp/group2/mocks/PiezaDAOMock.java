package org.utn.tacs.tp.group2.mocks;

import java.util.ArrayList;
import java.util.List;

import org.utn.tacs.tp.group2.dao.PiezaDAO;
import org.utn.tacs.tp.group2.exceptions.pieza.PiezaInexistenteException;
import org.utn.tacs.tp.group2.pieza.Auto;
import org.utn.tacs.tp.group2.pieza.CategoriaPieza;
import org.utn.tacs.tp.group2.pieza.EstadoPieza;
import org.utn.tacs.tp.group2.pieza.Pieza;

public class PiezaDAOMock implements PiezaDAO{

	private List<Pieza> piezas;
	
	public PiezaDAOMock() {
		this.piezas = new ArrayList<Pieza>();
	}
	
	public List<Pieza> findByCategoria(CategoriaPieza categoria) {
		List<Pieza> result = new ArrayList<Pieza>();
		for(Pieza pieza : this.piezas){
			if(pieza.getCategoria().equals(categoria))
				result.add(pieza);
		}
		return result;
	}

	public Pieza findByCodigo(String codigo) {
		Pieza result = null;
		for(Pieza pieza : this.piezas){
			if(pieza.getCodigo().equals(codigo)){
				result = pieza;
				break;
			}
		}
		if(result == null)
			throw new PiezaInexistenteException("No existe la pieza con código: '" + codigo + "'.");
		return result;
	}

	public List<Pieza> findByEstado(EstadoPieza estado) {
		List<Pieza> result = new ArrayList<Pieza>();
		for(Pieza pieza : this.piezas){
			if(pieza.getEstado().equals(estado))
				result.add(pieza);
		}
		return result;
	}

	public void save(Pieza pieza) {
		this.piezas.add(pieza);
	}

	public Pieza findByID(String id) {
		Pieza result = null;
		for(Pieza pieza : this.piezas){
			if(pieza.getId().equals(id)){
				result = pieza;
				break;
			}
		}
		if(result == null)
			throw new PiezaInexistenteException("No existe la pieza con id: '" + id + "'.");
		return result;
	}

	public List<Pieza> findByAuto(Auto auto) {
		List<Pieza> result = new ArrayList<Pieza>();
		for(Pieza pieza : this.piezas){
			if(pieza.getAutoOrigen().equals(auto))
				result.add(pieza);
		}
		return result;
	}

	public List<Pieza> findByEstadoAndCategoria(EstadoPieza estado, CategoriaPieza categoria) {
		List<Pieza> result = new ArrayList<Pieza>();
		for(Pieza pieza : this.piezas){
			if(pieza.getEstado().equals(estado) && pieza.getCategoria().equals(categoria))
				result.add(pieza);
		}
		return result;
	}

	public List<Pieza> findByEstadoAndAuto(EstadoPieza estado, Auto auto) {
		List<Pieza> result = new ArrayList<Pieza>();
		for(Pieza pieza : this.piezas){
			if(pieza.getAutoOrigen().equals(auto) && pieza.getEstado().equals(estado))
				result.add(pieza);
		}
		return result;
	}


}
