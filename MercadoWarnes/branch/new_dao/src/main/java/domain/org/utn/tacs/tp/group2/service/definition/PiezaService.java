package org.utn.tacs.tp.group2.service.definition;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.utn.tacs.tp.group2.daos.interfaces.PiezaDAO;
import org.utn.tacs.tp.group2.service.implementation.PiezaDTO;

/**
 * Interfaz del Servicio de Piezas.
 */
public interface PiezaService {

	@Transactional(propagation=Propagation.SUPPORTS)
	public PiezaDTO getPiezaById(String id);

	@Transactional(propagation=Propagation.SUPPORTS)
	public List<PiezaDTO> getPiezasByCategoria(String categoria);

	@Transactional(propagation=Propagation.SUPPORTS)
	public List<PiezaDTO> getPiezasByAuto(String autoId);

	@Transactional(propagation=Propagation.SUPPORTS)
	public List<PiezaDTO> getPiezasVendidasByAuto(String autoId);
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public int getPorcentajePiezasVendidasByAuto(String autoId);

	@Transactional(propagation=Propagation.SUPPORTS)
	public List<PiezaDTO> getAllPiezas();

	@Transactional(propagation=Propagation.SUPPORTS)
	public List<PiezaDTO> getPiezasByEstado(String estado);	
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public void setPiezaDAO(PiezaDAO dao);

	@Transactional(propagation=Propagation.SUPPORTS)
	public PiezaDAO getPiezaDAO();
	
}
