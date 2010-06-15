package org.utn.tacs.tp.group2.service.resources;

import org.restlet.Context;
import org.restlet.data.MediaType;
import org.restlet.data.Request;
import org.restlet.data.Response;
import org.restlet.resource.Representation;
import org.restlet.resource.Resource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.Variant;
import org.springframework.beans.factory.annotation.Autowired;
import org.utn.tacs.tp.group2.service.definition.PedidoService;

public class CreadorPedidoResource extends Resource{

	@Autowired
	private PedidoService pedidoService;
	
	public void init(Context context, Request request, Response response) {
		super.init(context, request, response);
		getVariants().add(new Variant(MediaType.TEXT_XML));
	}

	@Override
	public Representation represent(Variant variant) throws ResourceException {
//		Pedido nuevoPedido = this.pedidoService.crearPedido();
//		
//		return new StringRepresentation(new XStream().toXML(new PedidoDTO(nuevoPedido)), MediaType.TEXT_XML);
		return null;
	}
	
}
