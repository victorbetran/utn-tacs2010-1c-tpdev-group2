package org.utn.tacs.tp.group2.service.definition;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.utn.tacs.tp.group2.daos.interfaces.PedidoDAO;
import org.utn.tacs.tp.group2.daos.interfaces.PiezaDAO;
import org.utn.tacs.tp.group2.service.implementation.PedidoDTO;
import org.utn.tacs.tp.group2.service.implementation.PiezaDTO;

/**
 * Interfaz del Servicio de Pedidos.
 */
public interface PedidoService {
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public PedidoDTO getPedidoById(String id);
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void agregarPiezaAlPedido(String pedidoId, PiezaDTO piezaDTO);

	@Transactional(propagation=Propagation.REQUIRED)
	public void efectivizarPedido(String id);

	@Transactional(propagation=Propagation.REQUIRED)
	public void cancelarPedido(String id);

	@Transactional(propagation=Propagation.SUPPORTS)
	public List<PedidoDTO> getPedidosByEstado(String estado);

	@Transactional(propagation=Propagation.REQUIRED)
	public Long crearPedido(PedidoDTO pedidoDTO);

//	@Transactional(propagation=Propagation.SUPPORTS)
	public void setPedidoDAO(PedidoDAO dao);
	
//	@Transactional(propagation=Propagation.SUPPORTS)
	public PedidoDAO getPedidoDAO();
	
//	@Transactional(propagation=Propagation.SUPPORTS)
	public void setPiezaDAO(PiezaDAO dao);
	
//	@Transactional(propagation=Propagation.SUPPORTS)
	public PiezaDAO getPiezaDAO();
}
