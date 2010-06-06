package org.utn.tacs.tp.group2.service.implementation;

import java.math.BigDecimal;

import org.restlet.Context;
import org.restlet.data.MediaType;
import org.restlet.data.Request;
import org.restlet.data.Response;
import org.restlet.resource.Representation;
import org.restlet.resource.Resource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.StringRepresentation;
import org.restlet.resource.Variant;
import org.springframework.stereotype.Component;
import org.utn.tacs.tp.group2.pieza.Moneda;
import org.utn.tacs.tp.group2.pieza.Pieza;

import com.thoughtworks.xstream.XStream;

@Component
public class PiezaResource extends Resource {

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

	public void init(Context context, Request request, Response response) {
		super.init(context, request, response);
		getVariants().add(new Variant(MediaType.TEXT_XML));
	}

	@Override
	public Representation represent(Variant variant) throws ResourceException {

		String id = (String) getRequest().getAttributes().get("idPieza");
		id = id.toUpperCase();
		Pieza pieza = new Pieza("123456789",new BigDecimal(99), Moneda.Dolares);
		pieza.setId(Long.valueOf(id));
		return new StringRepresentation(new XStream().toXML(new PiezaDTO(pieza)), MediaType.TEXT_XML);
	}

}
