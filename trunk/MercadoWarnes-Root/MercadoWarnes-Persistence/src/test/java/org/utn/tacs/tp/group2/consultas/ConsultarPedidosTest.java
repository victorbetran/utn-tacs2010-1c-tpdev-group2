package org.utn.tacs.tp.group2.consultas;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.replay;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.utn.tacs.tp.group2.daos.interfaces.PedidoDAO;
import org.utn.tacs.tp.group2.daos.mocks.MockDAOFactory;
import org.utn.tacs.tp.group2.exceptions.pedido.PedidoInexistenteException;
import org.utn.tacs.tp.group2.pedido.EstadoPedido;
import org.utn.tacs.tp.group2.pedido.Pedido;

import com.eaio.uuid.UUID;

public class ConsultarPedidosTest {

	//********************************************
	//** ATRIBUTTES
	//********************************************
	private PedidoDAO dao;
	private int CANTIDAD_PEDIDOS_EFECTIVIZADOS;
	private int CANTIDAD_PEDIDOS_EN_CURSO;
	private int CANTIDAD_PEDIDOS_CANCELADOS;
	
	private UUID idPedido1 = new UUID();
	
	
	//********************************************
	//** SET UP
	//********************************************
	@Before
	public void setUp() throws Exception {
		this.dao = MockDAOFactory.getInstance().getPedidoDAO();
		
		this.dao.save(createPedidoMock(this.idPedido1, EstadoPedido.getEfectivo(null)));
		this.CANTIDAD_PEDIDOS_EFECTIVIZADOS = 1;
		
		this.dao.save(createPedidoMock(new UUID(), EstadoPedido.getEnCurso(null)));
		this.dao.save(createPedidoMock(new UUID(), EstadoPedido.getEnCurso(null)));
		this.CANTIDAD_PEDIDOS_EN_CURSO = 2;
		
		this.dao.save(createPedidoMock(new UUID(), EstadoPedido.getCancelado(null)));
		this.dao.save(createPedidoMock(new UUID(), EstadoPedido.getCancelado(null)));
		this.CANTIDAD_PEDIDOS_CANCELADOS = 2;
	}


	//********************************************
	//** TEST METHODS
	//********************************************
	/**
	 * Consulta un pedido existente en la BD por su ID
	 */
	@Test 
	public void consultarUnPedidoPorID(){
		Pedido pedido = this.dao.findByID(this.idPedido1);
		Assert.assertTrue(pedido.getId().equals(this.idPedido1));
	}
	
	/**
	 * Consulta un pedido que no existe en la BD por su ID
	 */
	@Test(expected=PedidoInexistenteException.class) 
	public void consultarUnPedidoInexistentePorID(){
		this.dao.findByID(new UUID());
	}
	
	/**
	 * Consulta las piezas reservadas.
	 */
	@Test 
	public void consultarPedidosPorEstado(){
		List<Pedido> pedidosEfectivizados = this.dao.findByEstado(EstadoPedido.getEfectivo(null));
		List<Pedido> pedidosEnCurso = this.dao.findByEstado(EstadoPedido.getEnCurso(null));
		List<Pedido> pedidosCancelados = this.dao.findByEstado(EstadoPedido.getCancelado(null));
		
		Assert.assertTrue(pedidosEfectivizados.size() == this.CANTIDAD_PEDIDOS_EFECTIVIZADOS);
		Assert.assertTrue(pedidosEnCurso.size() == this.CANTIDAD_PEDIDOS_EN_CURSO);
		Assert.assertTrue(pedidosCancelados.size() == this.CANTIDAD_PEDIDOS_CANCELADOS);
	}
	
	
	//********************************************
	//** PRIVATE METHODS
	//********************************************
	/**
	 * Crea un pedido a partir de un codigo y un estado.
	 */
	private Pedido createPedidoMock(UUID id, EstadoPedido estado) {
		Pedido mock = createMock(Pedido.class);
		expect(mock.getId()).andReturn(id).anyTimes();
		expect(mock.getEstado()).andReturn(estado).anyTimes();
		replay(mock);
		return mock;
	}
}
