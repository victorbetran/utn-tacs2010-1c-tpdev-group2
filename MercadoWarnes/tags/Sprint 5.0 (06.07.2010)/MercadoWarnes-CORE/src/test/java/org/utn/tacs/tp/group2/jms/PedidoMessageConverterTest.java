package org.utn.tacs.tp.group2.jms;

import java.util.ArrayList;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.ObjectMessage;

import org.apache.activemq.command.ActiveMQMapMessage;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.utn.tacs.tp.group2.pedido.EstadoPedido;
import org.utn.tacs.tp.group2.pedido.Pedido;
import org.utn.tacs.tp.group2.pieza.EstadoPieza;
import org.utn.tacs.tp.group2.pieza.Pieza;

import ar.edu.utn.frba.tacs.warnes.Cliente;
import ar.edu.utn.frba.tacs.warnes.Item;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class PedidoMessageConverterTest {

	@Autowired
	PedidoMessageConverter pedidoConverter;

	ar.edu.utn.frba.tacs.warnes.Pedido pedidoApi;

	// Este mensaje debe ser instanceof ObjectMessage
	ObjectMessage objectMessage;

	// Este mensaje NO debe ser instanceof ObjectMessage, puede ser de algun otro tipo
	MapMessage otherMessage;

	@Before
	public void setUp() throws Exception {

		pedidoApi = createPedidoApi();
		pedidoApi.getItems().add(this.createItem(2, "001122", new Double(20)));
		pedidoApi.getItems().add(this.createItem(5, "334455", new Double(90)));

		objectMessage = new ActiveMQObjectMessage();
		objectMessage.setObject(pedidoApi);

		otherMessage = new ActiveMQMapMessage();
	}

	/**
	 * Si el message recibido no es de la interface ObjectMessage debe tirar una
	 * MessageConversionException
	 * 
	 * @throws MessageConversionException
	 * @throws JMSException
	 */
	@Test(expected = MessageConversionException.class)
	public void fromMessageExceptionTest() throws MessageConversionException, JMSException {
		pedidoConverter.fromMessage(otherMessage);
	}

	/**
	 * Testeamos el fromMessage del Converter que convierte de un ObjectMessage que contiene al
	 * pedidoApi a nuestra clase de dominio Pedido.
	 * 
	 * @throws MessageConversionException
	 * @throws JMSException
	 */
	@Test
	public void fromMessageTest() throws MessageConversionException, JMSException {
		Pedido pedido = (Pedido) pedidoConverter.fromMessage(objectMessage);
		Assert.assertNotNull(pedido.getId());
		Assert.assertNotNull(pedido.getPiezas());
		Assert.assertEquals(pedido.getEstado(), EstadoPedido.getEfectivo());

		// Verificamos que las piezas tengan datos seteados
		Assert.assertFalse(pedido.getPiezas().isEmpty());
		for (Pieza pieza : pedido.getPiezas()) {
			Assert.assertNotNull(pieza.getCategoria());
			Assert.assertNotNull(pieza.getCodigo());
			Assert.assertNotNull(pieza.getAutoOrigen());
			Assert.assertEquals(pieza.getEstado(), EstadoPieza.getEstadoVendida());
		}
	}

	// **********************************
	// HELPERS
	// **********************************
	/**
	 * Crea un pedido de la API de TACS.
	 */
	public ar.edu.utn.frba.tacs.warnes.Pedido createPedidoApi() {
		ar.edu.utn.frba.tacs.warnes.Pedido pedidoApi = new ar.edu.utn.frba.tacs.warnes.Pedido();
		pedidoApi.setNumero(new Long(1));
		pedidoApi.setCliente(new Cliente());
		pedidoApi.setItems(new ArrayList<Item>());

		return pedidoApi;
	}

	/**
	 * Crea un item de la API de TACS.
	 * 
	 * @param cantidad
	 * @param codigo
	 * @param precioUnitario
	 * @return un item
	 */
	public Item createItem(Integer cantidad, String codigo, Double precioUnitario) {
		Item item = new Item();
		item.setCantidad(cantidad);
		item.setCodigo(codigo);
		item.setPrecioUnitario(precioUnitario);

		return item;
	}
}
