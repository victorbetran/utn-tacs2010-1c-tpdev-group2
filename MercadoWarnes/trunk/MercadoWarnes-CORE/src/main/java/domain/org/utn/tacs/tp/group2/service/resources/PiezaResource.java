package org.utn.tacs.tp.group2.service.resources;

import java.util.ArrayList;
import java.util.List;

import org.restlet.Context;
import org.restlet.data.MediaType;
import org.restlet.data.Request;
import org.restlet.data.Response;
import org.restlet.resource.Representation;
import org.restlet.resource.Resource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.StringRepresentation;
import org.restlet.resource.Variant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.utn.tacs.tp.group2.pieza.Pieza;
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
	public Representation represent(Variant variant) throws ResourceException {

		if(consultaById()){
			Pieza pieza = this.piezaService.getPiezaById(this.getPiezaId());
			if(pieza == null){
				return null;
			}
			return new StringRepresentation(new XStream().toXML(new PiezaDTO(pieza)), MediaType.TEXT_XML);
		} else if(consultaByCategoria()){
			return this.buildAnswerFrom(this.piezaService.getPiezasByCategoria(getCategoria()));
		} else if(consultaByAuto()){
			return this.buildAnswerFrom(this.piezaService.getPiezasByAuto(getAutoId()));
		} else if(consultaByEstado()){
			return this.buildAnswerFrom(this.piezaService.getPiezasReservadas());
		} else{
			return this.buildAnswerFrom(this.piezaService.getAllPiezas());
		}
	}

	private Representation buildAnswerFrom(List<Pieza> piezas){
		List<PiezaDTO> toReturn = new ArrayList<PiezaDTO>();
		
		for (Pieza pieza : piezas) {
			toReturn.add(new PiezaDTO(pieza));
		}

		return new StringRepresentation(new XStream().toXML(toReturn), MediaType.TEXT_XML);		
	}
	
	//********************************************
	//** PRIVATE IMPLEMENTATION
	//********************************************

	private boolean consultaById() {
		String id = (String) getRequest().getAttributes().get("idPieza");
		return id != null;
	}
	
	private boolean consultaByCategoria() {
		String id = (String) getRequest().getAttributes().get("categoria");
		return id != null;
	}
	
	private boolean consultaByAuto() {
		String id = (String) getRequest().getAttributes().get("idAuto");
		return id != null;
	}
	
	private boolean consultaByEstado() {
		String id = (String) getRequest().getAttributes().get("estado");
		return id != null;
	}
	
	private Long getPiezaId(){
		return Long.valueOf((String) getRequest().getAttributes().get("idPieza"));
	}

	private String getCategoria(){
		return (String) getRequest().getAttributes().get("categoria");
	}
	
	private String getAutoId(){
		return (String) getRequest().getAttributes().get("idAuto");
	}

	private String getEstado(){
		return (String) getRequest().getAttributes().get("estado");
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
