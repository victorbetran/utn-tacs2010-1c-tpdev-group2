package org.utn.tacs.tp.group2.service.resources;

import java.io.IOException;

import org.restlet.Context;
import org.restlet.data.MediaType;
import org.restlet.data.Request;
import org.restlet.data.Response;
import org.restlet.data.Status;
import org.restlet.resource.Representation;
import org.restlet.resource.Resource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.StringRepresentation;
import org.restlet.resource.Variant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.utn.tacs.tp.group2.exceptions.services.ComposicionPedidoInvalida;
import org.utn.tacs.tp.group2.pedido.EstadoPedido;
import org.utn.tacs.tp.group2.service.definition.PedidoService;
import org.utn.tacs.tp.group2.service.implementation.PedidoDTO;

import com.thoughtworks.xstream.XStream;

@Component
public class PedidosResource extends Resource {

	@Autowired
	private PedidoService pedidoService;
	
	public void init(Context context, Request request, Response response) {
		super.init(context, request, response);
		getVariants().add(new Variant(MediaType.TEXT_XML));
	}

	@Override
	public boolean allowDelete() {
		return false;
	}

	@Override
	public boolean allowGet() {
		return true;
	}

	@Override
	public boolean allowPost() {
		return false;
	}

	@Override
	public boolean allowPut() {
		return true;
	}
	
	private String estado;
	@Override
	public Representation represent(Variant variant) throws ResourceException {
		this.estado = getQuery().getFirstValue("estado");
		
		if(estado == null || EstadoPedido.estadoByDescripcion(this.estado) == null){
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return null;
		}
		
		return this.buildAnswerFrom(this.pedidoService.getPedidosByEstado(this.estado));
		
	}
	
	/**
	 * PUT
	 */
	@Override
	public void storeRepresentation(Representation entity) throws ResourceException {
		try {
			PedidoDTO pedido;
			pedido = (PedidoDTO) new XStream().fromXML(entity.getStream());

			this.pedidoService.crearPedido(pedido);
			getResponse().setStatus(Status.SUCCESS_CREATED);
		} catch (ComposicionPedidoInvalida e) {
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
		} catch (IOException e) {
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
		} catch (RuntimeException e) {
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
		}
	}
	
	//********************************************
	//** PRIVATE IMPLEMENTATION
	//********************************************
	
	private Representation buildAnswerFrom(Object o){
		return new StringRepresentation(new XStream().toXML(o), MediaType.TEXT_XML);		
	}
	
	//********************************************
	//** GETTER & SETTER
	//********************************************
	
	public PedidoService getPedidoService() {
		return pedidoService;
	}
	
	public void setPedidoService(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

}
