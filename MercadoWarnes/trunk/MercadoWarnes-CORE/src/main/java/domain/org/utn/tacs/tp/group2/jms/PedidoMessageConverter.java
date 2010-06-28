package org.utn.tacs.tp.group2.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.utn.tacs.tp.group2.pedido.EstadoPedido;
import org.utn.tacs.tp.group2.pedido.Pedido;
import org.utn.tacs.tp.group2.pieza.EstadoPieza;
import org.utn.tacs.tp.group2.pieza.Moneda;
import org.utn.tacs.tp.group2.pieza.Pieza;
import org.utn.tacs.tp.group2.pieza.Precio;

import ar.edu.utn.frba.tacs.warnes.Item;

public class PedidoMessageConverter implements MessageConverter {

	public PedidoMessageConverter() {
	}

	/**
	 * Convierte un mensaje recibido de la cola en un Pedido de nuestro dominio. No realiza
	 * conversion de objeto a mensaje ya que no realizamos envios a la cola.
	 */
	public Object fromMessage(Message message) throws JMSException, MessageConversionException {

		if (!(message instanceof ObjectMessage)) {
			throw new MessageConversionException("El mensaje no es un ObjectMessage");
		}

		// Casteamos el menssage a ObjectMessage
		ObjectMessage objectMessage = (ObjectMessage) message;

		// Obtenemos el pedido del mensaje que recibimos de la cola
		ar.edu.utn.frba.tacs.warnes.Pedido pedidoApi = (ar.edu.utn.frba.tacs.warnes.Pedido) objectMessage
				.getObject();

		// Creamos un pedido de nuestro dominio
		Pedido pedido = Pedido.create();

		// Tranformamos los items en piezas y las agregamos al pedido. Muchos atributos de la pieza
		// son tomados por Default.
		for (Item item : pedidoApi.getItems()) {
			for (int i = 0; i < item.getCantidad(); i++) {
				Pieza pieza = Pieza.createDefault();
				pieza.setCodigo(item.getCodigo());
				pieza.setPrecio(new Precio(Moneda.PESO, item.getPrecioUnitario()));
				pedido.addPieza(pieza);
				pieza.setEstado(EstadoPieza.getEstadoVendida());
			}
		}

		// Lo seteo manualmente para no tener problemas con lo de SOAP, creo.
		pedido.setEstado(EstadoPedido.getEfectivo());

		return pedido;
	}

	/**
	 * NO enviamos nada a la cola, NO necesitamos implementar el toMessage().
	 */
	public Message toMessage(Object object, Session session) throws JMSException,
			MessageConversionException {
		// do nothing
		return null;
	}

}
