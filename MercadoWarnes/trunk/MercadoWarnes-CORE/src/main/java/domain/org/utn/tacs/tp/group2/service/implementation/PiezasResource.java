package org.utn.tacs.tp.group2.service.implementation;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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
public class PiezasResource extends Resource{

	public String talkAsMaradona() {
		return "sigan mamando, es bueno para los huesos";
	}

	public Integer mundiales(String pais) {
		if(pais.equals("argentina"))
			return 2;
		if(pais.equals("brazil"))
			return 5;
		return 0;
	}

	public PiezaDTO damePieza() {
		return new PiezaDTO(new Pieza("CODIGO",new BigDecimal(99), Moneda.Dolares));
	}
	
	//*************************************//
	
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
		return false;
	}
	
	public Representation represent(Variant variant) throws ResourceException {
		XStream xStream = new XStream();
		Set<PiezaDTO> piezas = new HashSet<PiezaDTO>();
		piezas.add(damePieza());
		return new StringRepresentation(xStream.toXML(piezas), MediaType.TEXT_XML);
	}

}
