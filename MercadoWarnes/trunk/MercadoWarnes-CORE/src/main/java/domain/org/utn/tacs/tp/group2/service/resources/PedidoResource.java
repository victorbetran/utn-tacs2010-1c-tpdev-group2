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
import org.utn.tacs.tp.group2.pedido.Pedido;
import org.utn.tacs.tp.group2.service.definition.PedidoService;
import org.utn.tacs.tp.group2.service.implementation.PedidoDTO;

import com.thoughtworks.xstream.XStream;

@Component
public class PedidoResource extends Resource{

	@Autowired
	private PedidoService pedidoService;
	
	public void init(Context context, Request request, Response response) {
		super.init(context, request, response);
		getVariants().add(new Variant(MediaType.TEXT_XML));
	}

	@Override
	public Representation represent(Variant variant) throws ResourceException {
		
		if(consultaById()){
			Pedido pedido = this.pedidoService.getPedidoById(this.getPedidoId());
			if(pedido == null){
				return null;
			}
			return new StringRepresentation(new XStream().toXML(new PedidoDTO(pedido)), MediaType.TEXT_XML);
		} else if(consultaByState()){
			return this.buildAnswerFrom(this.pedidoService.getPedidosByEstado(getEstado()));
		} else if(agregaPieza()){
			return new StringRepresentation(new XStream().toXML(new PedidoDTO(this.pedidoService.agregarPiezaAlPedido(getPedidoId(), getPiezaId()))), MediaType.TEXT_XML);			
		} else if(aplicaAccionSobrePedido()){
			String accion = getAccion();
			if(accion.equals("efectivizar"))
				return new StringRepresentation(new XStream().toXML(new PedidoDTO(this.pedidoService.efectivizarPedido(getPedidoId()))), MediaType.TEXT_XML);
			else if(accion.equals("cancelar"))
				return new StringRepresentation(new XStream().toXML(new PedidoDTO(this.pedidoService.cancelarPedido(getPedidoId()))), MediaType.TEXT_XML);
		}
		
		return null;
		
	}

	private Representation buildAnswerFrom(List<Pedido> pedidos){
		List<PedidoDTO> toReturn = new ArrayList<PedidoDTO>();
		
		for (Pedido pedido : pedidos) {
			toReturn.add(new PedidoDTO(pedido));
		}

		return new StringRepresentation(new XStream().toXML(toReturn), MediaType.TEXT_XML);		
	}
	
	private String getPedidoId() {
		return (String) getRequest().getAttributes().get("idPedido");
	}

	private boolean consultaById() {
		return (String) getRequest().getAttributes().get("idPedido") != null
		&& (String) getRequest().getAttributes().get("idPieza") == null
		&& (String) getRequest().getAttributes().get("accion") == null;
	}

	private String getEstado() {
		return (String) getRequest().getAttributes().get("estado");
	}
	
	private boolean consultaByState() {
		return (String) getRequest().getAttributes().get("estado") != null;
	}
	
	private boolean agregaPieza() {
		return (String) getRequest().getAttributes().get("idPedido") != null
		&& (String) getRequest().getAttributes().get("idPieza") != null
		&& (String) getRequest().getAttributes().get("accion") == null;
	}
	
	private String getPiezaId(){
		return (String) getRequest().getAttributes().get("idPieza");
	}

	private String getAccion(){
		return (String) getRequest().getAttributes().get("accion");
	}
	
	private boolean aplicaAccionSobrePedido(){
		return (String) getRequest().getAttributes().get("idPedido") != null
		&& (String) getRequest().getAttributes().get("idPieza") == null
		&& (String) getRequest().getAttributes().get("accion") != null;
	}
	
}
