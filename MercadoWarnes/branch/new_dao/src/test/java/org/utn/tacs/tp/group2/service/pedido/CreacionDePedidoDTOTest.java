package org.utn.tacs.tp.group2.service.pedido;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.utn.tacs.tp.group2.pedido.Pedido;
import org.utn.tacs.tp.group2.pieza.Moneda;
import org.utn.tacs.tp.group2.pieza.Pieza;
import org.utn.tacs.tp.group2.service.implementation.PedidoDTO;

public class CreacionDePedidoDTOTest {

	private PedidoDTO pedidoDto;
	private Pedido pedidoModel;
	private Pieza unaPiezaModel;
	private Pieza otraPiezaModel;
	
	@Before
	public void setUp() {
		this.unaPiezaModel = new Pieza("CodigoPieza",10,Moneda.Dolares);
		this.otraPiezaModel = new Pieza("CodigoPieza",10,Moneda.Dolares);
		
		this.pedidoModel = Pedido.create();
		this.pedidoModel.addPieza(unaPiezaModel);
		this.pedidoModel.addPieza(otraPiezaModel);
		
		this.pedidoDto = new PedidoDTO(this.pedidoModel);
		
	}
	
	@Test
	public void validarPiezas() {
		List<String> piezasId = this.pedidoDto.getPiezas();
		
		Assert.assertEquals(2, piezasId.size());
		Assert.assertTrue(piezasId.contains(this.unaPiezaModel.getId().toString()));
		Assert.assertTrue(piezasId.contains(this.otraPiezaModel.getId().toString()));
	}
	
	@Test
	public void validarId() {
		Assert.assertEquals(this.pedidoModel.getId().toString(), this.pedidoDto.getId());
	}
	
	@Test
	public void validarEstado() {
		Assert.assertEquals(this.pedidoModel.getEstado().toString(), this.pedidoDto.getEstado());
	}
	
}
