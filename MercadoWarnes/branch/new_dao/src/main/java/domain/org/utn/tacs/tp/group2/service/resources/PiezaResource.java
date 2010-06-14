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
import org.restlet.resource.StringRepresentation;
import org.restlet.resource.Variant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.utn.tacs.tp.group2.service.definition.PiezaService;
import org.utn.tacs.tp.group2.service.implementation.PiezaDTO;

import com.thoughtworks.xstream.XStream;

@Component
public class PiezaResource extends Resource {

	@Autowired
	private PiezaService piezaService;
	
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
	
	/**
	 * GET
	 */
	@Override
	public Representation represent(Variant variant) throws ResourceException {
		String id = (String) getRequest().getAttributes().get("id-pieza");
		
		if (!isValidID(id)) {
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return null;
		}

		PiezaDTO pieza = this.piezaService.getPiezaById(id);
		if (pieza == null) {
			getResponse().setStatus(Status.CLIENT_ERROR_NOT_FOUND);
			return null;
		}
		
		return new StringRepresentation(new XStream().toXML(pieza), MediaType.TEXT_XML);	
	}

	private static Pattern numericCheckPattern = Pattern.compile("^\\d+$");
	private boolean isValidID(String id) {
		if( id == null)
			return false;
		
		Matcher matcher = numericCheckPattern.matcher(id); 
		return matcher.find();
	}
	
//	/**
//	 * POST
//	 */
//	@Override
//	public void acceptRepresentation(Representation entity)	throws ResourceException {
//		Form form = new Form(entity);
//		
//		String codigo = form.getFirstValue("codigo");
//		String estado = form.getFirstValue("estado");
//		String descripcion = form.getFirstValue("descripcion");
//		String categoria = form.getFirstValue("categoria");
//		String autoOrigen = form.getFirstValue("autoOrigen");
//		String precio = form.getFirstValue("precio");
//		String moneda = form.getFirstValue("moneda");
//		
//		if( areValid(codigo,estado,descripcion,categoria,autoOrigen,precio,moneda) ){
//			
//		}
//		
//		getResponse().setStatus(Status.SUCCESS_CREATED);
//		super.acceptRepresentation(entity);
//	}
//
//	private boolean areValid(String codigo, String estado, String descripcion, String categoria, String autoOrigen, String precio, String moneda) {
//		if(codigo == null || estado == null || categoria == null || autoOrigen == null || precio == null || moneda == null){
//			return false;
//		}
//		
//		if(EstadoPieza.estadoByDescripcion(estado) == null){
//			return false;
//		}
//		
//		if(categoria.isEmpty()){
//			return false;
//		}
//		
//		if(this.isValidID(autoOrigen)){
//			
//		}
//		
//		return true;
//	}

	//********************************************
	//** GETTER & SETTER
	//********************************************
	public PiezaService getPiezaService() {
		return piezaService;
	}
	
	public void setPiezaService(PiezaService piezaService) {
		this.piezaService = piezaService;
	}
	
}
