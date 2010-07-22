package org.utn.tacs.tp.group2.daos.interfaces;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.utn.tacs.tp.group2.pieza.Auto;
import org.utn.tacs.tp.group2.pieza.EstadoPieza;
import org.utn.tacs.tp.group2.pieza.Pieza;


public interface PiezaDAO {

	@Transactional(propagation = Propagation.REQUIRED)
	public void save(Pieza pieza);

	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(Pieza pieza);

	@Transactional(propagation = Propagation.SUPPORTS)
	public Boolean isPersisted(Pieza pieza);

	@Transactional(propagation = Propagation.SUPPORTS)
	public Boolean existsId(Long id);

	@Transactional(propagation = Propagation.SUPPORTS)
	public Pieza findByID(final Long id);

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Pieza> findAll();

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Pieza> findByAuto(final Auto auto);

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Pieza> findByAutoId(Long autoId);

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Pieza> findByCategoria(final String categoria);

	@Transactional(propagation = Propagation.SUPPORTS)
	public Pieza findByCodigo(String codigo);

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Pieza> findByEstado(EstadoPieza estado);

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Pieza> findByEstadoAndAutoId(EstadoPieza estado, Long autoId);
	
}
