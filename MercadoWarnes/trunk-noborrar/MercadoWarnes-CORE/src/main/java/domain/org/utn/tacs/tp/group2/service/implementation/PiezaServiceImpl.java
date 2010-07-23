package org.utn.tacs.tp.group2.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.utn.tacs.tp.group2.daos.exceptions.PiezaInexistenteException;
import org.utn.tacs.tp.group2.daos.interfaces.PiezaDAO;
import org.utn.tacs.tp.group2.pieza.EstadoPieza;
import org.utn.tacs.tp.group2.pieza.Pieza;
import org.utn.tacs.tp.group2.service.definition.PiezaService;

/**
 * Implementacion del Servicio de Piezas.
 */

public class PiezaServiceImpl implements PiezaService{

	@Autowired()
	private PiezaDAO piezaDAO;

	public PiezaDTO getPiezaById(String id) {
		try{
			return new PiezaDTO(piezaDAO.findByID(Long.valueOf(id)));
		}catch (PiezaInexistenteException e) {
			return null;
		}
	}
	
	public List<PiezaDTO> getAllPiezas() {
		return this.getDTOList(this.piezaDAO.findAll());
	}
	
	public List<PiezaDTO> getPiezasByAuto(String autoId) {
		return this.getDTOList(piezaDAO.findByAutoId(Long.valueOf(autoId)));
	}
	
	public List<PiezaDTO> getPiezasByCategoria(String categoria) {
		return this.getDTOList(piezaDAO.findByCategoria(categoria));
	}
	
	public List<PiezaDTO> getPiezasByEstado(String estado) {
		return this.getDTOList(piezaDAO.findByEstado(EstadoPieza.estadoByDescripcion(estado)));
	}
	
	public List<PiezaDTO> getPiezasVendidasByAuto(String autoId) {
		return this.getDTOList(piezaDAO.findByEstadoAndAutoId(EstadoPieza.getEstadoVendida(), Long.valueOf(autoId)));
	}
	
	public int getPorcentajePiezasVendidasByAuto(String autoId) {
		int numeroPiezasVendidas = this.getPiezasByAuto(autoId).size();
		int numeroPiezasVendidasByAuto = this.getPiezasVendidasByAuto(autoId).size();
				
		return (numeroPiezasVendidasByAuto*100)/numeroPiezasVendidas;
	}

	//********************************************
	//** PRIVATE METHODS
	//********************************************
	
	private List<PiezaDTO> getDTOList(List<Pieza> list){
		List<PiezaDTO> nlist = new ArrayList<PiezaDTO>();
		
		for (Pieza pieza : list) {
			nlist.add(new PiezaDTO(pieza));
		}
		
		return nlist;
	}
	
	// ********************************************
	// ** GETTERS & SETTERS
	// ********************************************

	public void setPiezaDAO(PiezaDAO piezaDAO) {
		this.piezaDAO = piezaDAO;
	}

	public PiezaDAO getPiezaDAO() {
		return piezaDAO;
	}

}
