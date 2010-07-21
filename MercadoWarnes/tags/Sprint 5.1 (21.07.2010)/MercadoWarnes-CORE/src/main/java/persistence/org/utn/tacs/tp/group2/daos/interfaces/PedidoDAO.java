package org.utn.tacs.tp.group2.daos.interfaces;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.utn.tacs.tp.group2.pedido.EstadoPedido;
import org.utn.tacs.tp.group2.pedido.Pedido;

public interface PedidoDAO {

	@Transactional(propagation = Propagation.REQUIRED)
	public void save(Pedido t);

	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(Pedido t);

	@Transactional(propagation = Propagation.SUPPORTS)
	public Boolean isPersisted(Pedido t);

	@Transactional(propagation = Propagation.SUPPORTS)
	public Boolean existsId(Long id);

	@Transactional(propagation = Propagation.SUPPORTS)
	public Pedido findByID(final Long id);

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Pedido> findAll();

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Pedido> findByEstado(EstadoPedido estado);
	
}
