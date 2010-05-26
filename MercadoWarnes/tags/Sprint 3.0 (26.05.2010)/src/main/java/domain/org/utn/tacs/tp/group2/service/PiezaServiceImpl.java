package org.utn.tacs.tp.group2.service;

import org.utn.tacs.tp.group2.daos.interfaces.PiezaDAO;
import org.utn.tacs.tp.group2.pieza.Pieza;


public class PiezaServiceImpl implements PiezaService{
	
	private PiezaDAO piezaDAO;
	
	public Pieza crearPieza(){
		Pieza pieza = new Pieza();
		piezaDAO.save(pieza);
		return pieza;
	}
	
	
	public void setPiezaDAO(PiezaDAO piezaDAO) {
		this.piezaDAO = piezaDAO;
	}

	public PiezaDAO getPiezaDAO() {
		return piezaDAO;
	}
}
