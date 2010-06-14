package org.utn.tacs.tp.group2.service.resources;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import org.utn.tacs.tp.group2.pedido.Pedido;
import org.utn.tacs.tp.group2.service.definition.PedidoService;
import org.utn.tacs.tp.group2.service.implementation.PedidoDTO;

import com.thoughtworks.xstream.XStream;

@Component
public class PedidoResource extends Resource{

	@Autowired(required=true)
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
		return true;
	}

	@Override
	public boolean allowPut() {
		return true;
	}
	
	/**
	 * GET
	 */
	@Override
	public Representation represent(Variant variant) throws ResourceException {
		String id = (String) getRequest().getAttributes().get("id-pedido");
		
		if (!isValidID(id)) {
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return null;
		}

		PedidoDTO pedido = this.pedidoService.getPedidoById(id);
		if (pedido == null) {
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_FOUND);
			return null;
		}
		
		return new StringRepresentation(new XStream().toXML(pedido), MediaType.TEXT_XML);
	}

	private static Pattern numericCheckPattern = Pattern.compile("^\\d+$");
	private boolean isValidID(String id) {
		if( id == null)
			return false;
		
		Matcher matcher = numericCheckPattern.matcher(id); 
		return matcher.find();
	}

	/**
	 * POST
	 */
	@Override
	public void acceptRepresentation(Representation entity)	throws ResourceException {

		try {
			Pedido pedido = (Pedido) new XStream().fromXML(entity.getStream());
			this.pedidoService.pedidoCreado(pedido);
		} catch (IOException e) {
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
		}

	}
	
}
