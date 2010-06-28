package org.utn.tacs.tp.group2.service.resources;

import java.util.Date;

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
import org.utn.tacs.tp.group2.daos.interfaces.PiezaDAO;
import org.utn.tacs.tp.group2.pieza.Auto;
import org.utn.tacs.tp.group2.pieza.Moneda;
import org.utn.tacs.tp.group2.pieza.Pieza;

import com.thoughtworks.xstream.XStream;

@Component
public class FakeValuesLoaderResource extends Resource{

	private Pieza unaPiezaPremiumDeAutoA;
	private Pieza unaPiezaMediumDeAutoA;
	private Pieza otraPiezaMediumDeAutoA;
	private Pieza unaPiezaMediumDeAutoB;
	
	private static boolean first = true;
	
	@Autowired
	private PiezaDAO piezaDao;
	
	public void init(Context context, Request request, Response response) {
		super.init(context, request, response);
		getVariants().add(new Variant(MediaType.TEXT_XML));
		
		if(first){
			
			Auto autoA = Auto.createAuto("EXP-074", "AK-47", 2009, new Date());
			Auto autoB = Auto.createAuto("EXP-174", "AK-47", 2009, new Date());
			
			unaPiezaPremiumDeAutoA = new Pieza("PIEZA 1",50,Moneda.DOLAR);
			unaPiezaPremiumDeAutoA.setCategoria("PREMIUM");
			unaPiezaPremiumDeAutoA.setAutoOrigen(autoA);
			
			unaPiezaMediumDeAutoA = new Pieza("PIEZA 2",22,Moneda.PESO);
			unaPiezaMediumDeAutoA.setCategoria("MEDIUM");
			unaPiezaMediumDeAutoA.setAutoOrigen(autoA);
			
			otraPiezaMediumDeAutoA = new Pieza("PIEZA 3",45,Moneda.PESO);
			otraPiezaMediumDeAutoA.setCategoria("MEDIUM");
			otraPiezaMediumDeAutoA.setAutoOrigen(autoA);
			
			unaPiezaMediumDeAutoB = new Pieza("PIEZA 12",25,Moneda.PESO);
			unaPiezaMediumDeAutoB.setCategoria("MEDIUM");
			unaPiezaMediumDeAutoB.setAutoOrigen(autoB);
			
			this.piezaDao.save(unaPiezaPremiumDeAutoA);
			this.piezaDao.save(unaPiezaMediumDeAutoA);
			this.piezaDao.save(otraPiezaMediumDeAutoA);
			this.piezaDao.save(unaPiezaMediumDeAutoB);
			
			first = false;
		}
	}
	
	@Override
	public Representation represent(Variant variant) throws ResourceException {
		return new StringRepresentation(new XStream().toXML("CARGADOS!"), MediaType.TEXT_XML);
	}
	
}
