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
	
//	private Pieza unaPiezaPremiumDeAutoA;
//	private Pieza unaPiezaMediumDeAutoA;
//	private Pieza unaPiezaMediumDeAutoB;
//	
//	private static boolean blah = true;
	
	public void init(Context context, Request request, Response response) {
		super.init(context, request, response);
		getVariants().add(new Variant(MediaType.TEXT_XML));
		
//		if(blah){
//			PiezaDAOMockToTestPiezaServiceBehavior piezaDAO = new PiezaDAOMockToTestPiezaServiceBehavior();
//			((PiezaServiceImpl)piezaService).setPiezaDAO(piezaDAO);
//			
//			Auto autoA = Auto.createAuto("EXP-074", "AK-47", 2009, new Date());
//			
//			unaPiezaPremiumDeAutoA = new Pieza("PIEZA 1",new BigDecimal(50),Moneda.Dolares);
//			unaPiezaPremiumDeAutoA.setCategoria("PREMIUM");
//			unaPiezaPremiumDeAutoA.setAutoOrigen(autoA);
//			
//			unaPiezaMediumDeAutoA = new Pieza("PIEZA 2",new BigDecimal(22),Moneda.Pesos);
//			unaPiezaMediumDeAutoA.setCategoria("MEDIUM");
//			unaPiezaMediumDeAutoA.setAutoOrigen(autoA);
//			
//			unaPiezaMediumDeAutoB = new Pieza("PIEZA 3",new BigDecimal(45),Moneda.Pesos);
//			unaPiezaMediumDeAutoB.setCategoria("MEDIUM");
//			unaPiezaMediumDeAutoB.setAutoOrigen(autoA);
//			
//			piezaDAO.save(unaPiezaPremiumDeAutoA);
//			piezaDAO.save(unaPiezaMediumDeAutoA);
//			piezaDAO.save(unaPiezaMediumDeAutoB);
//			
//			blah = false;
//		}
	}

	@Override
	public Representation represent(Variant variant) throws ResourceException {

		if(consultaById()){
			Pieza pieza = this.piezaService.getPiezaById(this.getId());
			
			if(pieza == null){
				return null;
			}
			
			return new StringRepresentation(new XStream().toXML(new PiezaDTO()), MediaType.TEXT_XML);
			
		}else{
			List<PiezaDTO> toReturn = new ArrayList<PiezaDTO>();
			
			for (Pieza pieza : this.piezaService.getAllPiezas()) {
				toReturn.add(new PiezaDTO(pieza));
			}

			return new StringRepresentation(new XStream().toXML(toReturn), MediaType.TEXT_XML);
		}
		
	}
	
	//********************************************
	//** PRIVATE IMPLEMENTATION
	//********************************************

	private boolean consultaById() {
		String id = (String) getRequest().getAttributes().get("idPieza");
		return id != null && !id.equals("all");
	}
	
	private Long getId(){
		return Long.valueOf((String) getRequest().getAttributes().get("idPieza"));
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
