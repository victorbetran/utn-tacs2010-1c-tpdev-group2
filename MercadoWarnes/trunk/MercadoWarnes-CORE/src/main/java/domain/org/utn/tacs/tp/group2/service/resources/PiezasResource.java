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
import org.utn.tacs.tp.group2.pieza.EstadoPieza;
import org.utn.tacs.tp.group2.service.definition.PiezaService;

import com.thoughtworks.xstream.XStream;

@Component
public class PiezasResource extends Resource {

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
	
	private String categoria;
	private String estado;
	private String auto;
	@Override
	public Representation represent(Variant variant) throws ResourceException {
		this.loadParameters();
		
		if(!allParametersOk()){
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return null;
		}
		
		if(this.categoria != null){
			return this.buildAnswerFrom(this.piezaService.getPiezasByCategoria(this.categoria));
		} else if (this.estado != null) {
			return this.buildAnswerFrom(this.piezaService.getPiezasByEstado(this.estado));
		} else if (this.auto != null) {
			return this.buildAnswerFrom(this.piezaService.getPiezasByAuto(this.auto));
		} 
		
		return this.buildAnswerFrom(this.piezaService.getAllPiezas());			
	}
	
	
	private void loadParameters() {
		this.categoria = getQuery().getFirstValue("categoria");
		this.estado = getQuery().getFirstValue("estado");
		this.auto = getQuery().getFirstValue("id-auto");
	}

	private boolean allParametersOk() {
		
		if(estado != null){
			return EstadoPieza.estadoByDescripcion(estado) != null;
		}
		
		if(auto != null){
			return this.isValidID(auto);
		}
		
		return true;
	}

	private static Pattern numericCheckPattern = Pattern.compile("^\\d+$");
	private boolean isValidID(String id) {
		if( id == null)
			return false;
		
		Matcher matcher = numericCheckPattern.matcher(id); 
		return matcher.find();
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
	
	public PiezaService getPiezaService() {
		return piezaService;
	}
	
	public void setPiezaService(PiezaService piezaService) {
		this.piezaService = piezaService;
	}

}
