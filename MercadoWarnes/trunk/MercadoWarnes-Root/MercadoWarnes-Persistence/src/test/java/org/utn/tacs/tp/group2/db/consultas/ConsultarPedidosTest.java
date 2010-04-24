package org.utn.tacs.tp.group2.db.consultas;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.replay;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.utn.tacs.tp.group2.daos.implementations.PedidoDAOImpl;
import org.utn.tacs.tp.group2.daos.interfaces.PedidoDAO;
import org.utn.tacs.tp.group2.exceptions.pedido.PedidoInexistenteException;
import org.utn.tacs.tp.group2.mock.daos.MockDAOFactory;
import org.utn.tacs.tp.group2.pedido.EstadoPedido;
import org.utn.tacs.tp.group2.pedido.Pedido;
import org.utn.tacs.tp.group2.utils.UUIDGenerator;


public class ConsultarPedidosTest {

	//********************************************
	//** ATRIBUTTES
	//********************************************
	private PedidoDAO dao;
	private int CANTIDAD_PEDIDOS_EFECTIVIZADOS;
	private int CANTIDAD_PEDIDOS_EN_CURSO;
	private int CANTIDAD_PEDIDOS_CANCELADOS;
	
	private Long idPedido1 = UUIDGenerator.getInstance().getId();
	
	
	//********************************************
	//** SET UP
	//********************************************
	@Before
	public void setUp() {
//		this.dao = MockDAOFactory.getInstance().getPedidoDAO();
//		
//		this.dao.save(createPedidoMock(this.idPedido1, EstadoPedido.getEfectivo(null)));
//		this.CANTIDAD_PEDIDOS_EFECTIVIZADOS = 1;
//		
//		this.dao.save(createPedidoMock(UUIDGenerator.getInstance().getId(), EstadoPedido.getEnCurso(null)));
//		this.dao.save(createPedidoMock(UUIDGenerator.getInstance().getId(), EstadoPedido.getEnCurso(null)));
//		this.CANTIDAD_PEDIDOS_EN_CURSO = 2;
//		
//		this.dao.save(createPedidoMock(UUIDGenerator.getInstance().getId(), EstadoPedido.getCancelado(null)));
//		this.dao.save(createPedidoMock(UUIDGenerator.getInstance().getId(), EstadoPedido.getCancelado(null)));
//		this.CANTIDAD_PEDIDOS_CANCELADOS = 2;
	}

	@Test
	public void savePiezaEnDisco(){
		PedidoDAO pedidoDao = new PedidoDAOImpl();
		Pedido pedidoPersistible = new Pedido();
		
		pedidoDao.save(pedidoPersistible);
		
		Pedido pedidoPersistido = pedidoDao.findByID(pedidoPersistible.getId());
		
		Assert.assertEquals(pedidoPersistible, pedidoPersistido);
		
	}

	//********************************************
	//** TEST METHODS
	//********************************************
//	/**
//	 * Consulta un pedido existente en la BD por su ID
//	 */
//	@Test 
//	public void consultarUnPedidoPorID(){
//		Pedido pedido = this.dao.findByID(this.idPedido1);
//		Assert.assertTrue(pedido.getId().equals(this.idPedido1));
//	}
//	
//	/**
//	 * Consulta un pedido que no existe en la BD por su ID
//	 */
//	@Test(expected=PedidoInexistenteException.class) 
//	public void consultarUnPedidoInexistentePorID(){
//		this.dao.findByID(UUIDGenerator.getInstance().getId());
//	}
//	
//	/**
//	 * Consulta las piezas reservadas.
//	 */
//	@Test 
//	public void consultarPedidosPorEstado(){
//		List<Pedido> pedidosEfectivizados = this.dao.findByEstado(EstadoPedido.getEfectivo(null));
//		List<Pedido> pedidosEnCurso = this.dao.findByEstado(EstadoPedido.getEnCurso(null));
//		List<Pedido> pedidosCancelados = this.dao.findByEstado(EstadoPedido.getCancelado(null));
//		
//		Assert.assertTrue(pedidosEfectivizados.size() == this.CANTIDAD_PEDIDOS_EFECTIVIZADOS);
//		Assert.assertTrue(pedidosEnCurso.size() == this.CANTIDAD_PEDIDOS_EN_CURSO);
//		Assert.assertTrue(pedidosCancelados.size() == this.CANTIDAD_PEDIDOS_CANCELADOS);
//	}
//	
//	
//	//********************************************
//	//** PRIVATE METHODS
//	//********************************************
//	/**
//	 * Crea un pedido a partir de un codigo y un estado.
//	 */
//	private Pedido createPedidoMock(Long id, EstadoPedido estado) {
//		Pedido mock = createMock(Pedido.class);
//		expect(mock.getId()).andReturn(id).anyTimes();
//		expect(mock.getEstado()).andReturn(estado).anyTimes();
//		replay(mock);
//		return mock;
//	}
	
}
