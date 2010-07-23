package org.utn.tacs.tp.group2.service.resources;

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
import org.restlet.resource.Variant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.utn.tacs.tp.group2.service.definition.PedidoService;

@Component
public class PedidoManejadorResource extends Resource {

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
		return false;
	}

	@Override
	public boolean allowPost() {
		return true;
	}

	@Override
	public boolean allowPut() {
		return false;
	}
	
	@Override
	public void acceptRepresentation(Representation entity)	throws ResourceException {
		String id = (String) getRequest().getAttributes().get("id-pedido");
		String accion = (String) getRequest().getAttributes().get("accion");
		
		if (!isValidID(id) || (!accion.toUpperCase().equals("EFECTIVIZAR") && !accion.toUpperCase().equals("CANCELAR"))) {
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
		} else {
			try {
				if(accion.toUpperCase().equals("EFECTIVIZAR"))
					this.pedidoService.efectivizarPedido(id);
				else
					this.pedidoService.cancelarPedido(id);
				
				getResponse().setStatus(Status.SUCCESS_NO_CONTENT);
			} catch (RuntimeException e) {
				getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			}
		}
	}
	
	private static Pattern numericCheckPattern = Pattern.compile("^\\d+$");
	private boolean isValidID(String id) {
		if( id == null)
			return false;
		
		Matcher matcher = numericCheckPattern.matcher(id); 
		return matcher.find();
	}
	
}
