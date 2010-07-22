package org.utn.tacs.tp.group2.service.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import org.utn.tacs.tp.group2.exceptions.services.ComposicionPedidoInvalida;
import org.utn.tacs.tp.group2.pedido.EstadoPedido;
import org.utn.tacs.tp.group2.service.definition.PedidoService;
import org.utn.tacs.tp.group2.service.implementation.PedidoDTO;
import org.utn.tacs.tp.group2.service.implementation.PiezaDTO;

import com.thoughtworks.xstream.XStream;

@Component
public class PedidoResource extends Resource {

	@Autowired(required = true)
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

		return new StringRepresentation(new XStream().toXML(pedido),
				MediaType.TEXT_XML);
	}

	/**
	 * POST
	 */
	@Override
	public void acceptRepresentation(Representation entity)
			throws ResourceException {
		String id = (String) getRequest().getAttributes().get("id-pedido");

		if (!isValidID(id)) {
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
		} else {
			try {
				PiezaDTO pedido;
				pedido = (PiezaDTO) new XStream().fromXML(entity.getStream());

				this.pedidoService.agregarPiezaAlPedido(id, pedido);
				getResponse().setStatus(Status.SUCCESS_NO_CONTENT);
			} catch (ComposicionPedidoInvalida e) {
				getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			} catch (IOException e) {
				getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			} catch (RuntimeException e) {
				getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			}
		}
	}

	/**
	 * PUT
	 */
	@Override
	public void storeRepresentation(Representation entity)
			throws ResourceException {
		String id = (String) getRequest().getAttributes().get("id-pedido");

		try {
			String value = convertStreamToString(entity.getStream()).replaceAll("^ESTADO=","").trim();

			if (!isValidID(id)
					|| (!value.equals(EstadoPedido.getEfectivo().toString()) && !value
							.equals(EstadoPedido.getCancelado().toString()))) {
				getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			} else {
				if (value.equals(EstadoPedido.getEfectivo().toString()))
					this.pedidoService.efectivizarPedido(id);
				else
					this.pedidoService.cancelarPedido(id);

				getResponse().setStatus(Status.SUCCESS_OK);
			}
		} catch (IOException e) {
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
		}

	}

	private String convertStreamToString(InputStream is) {
		try{
			if (is != null) {
				StringBuilder sb = new StringBuilder();
				String line;
				try {
					BufferedReader reader = new BufferedReader(	new InputStreamReader(is, "UTF-8"));
					while ((line = reader.readLine()) != null) {
						sb.append(line).append("\n");
					}
				} finally {
					is.close();
				}
				return sb.toString();
			} else {
				return "";
			}
		}
		catch (Exception e) {
			return "";
		}
	}

	/*
	 * 
	 * @Override public void acceptRepresentation(Representation entity) throws
	 * ResourceException { String id = (String)
	 * getRequest().getAttributes().get("id-pedido"); String accion = (String)
	 * getRequest().getAttributes().get("accion");
	 * 
	 * if (!isValidID(id) || (!accion.toUpperCase().equals("EFECTIVIZAR") &&
	 * !accion.toUpperCase().equals("CANCELAR"))) {
	 * getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST); } else { try {
	 * if(accion.toUpperCase().equals("EFECTIVIZAR"))
	 * this.pedidoService.efectivizarPedido(id); else
	 * this.pedidoService.cancelarPedido(id);
	 * 
	 * getResponse().setStatus(Status.SUCCESS_NO_CONTENT); } catch
	 * (RuntimeException e) {
	 * getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST); } } }
	 * 
	 * private static Pattern numericCheckPattern = Pattern.compile("^\\d+$");
	 * private boolean isValidID(String id) { if( id == null) return false;
	 * 
	 * Matcher matcher = numericCheckPattern.matcher(id); return matcher.find();
	 * }
	 */

	private static Pattern numericCheckPattern = Pattern.compile("^\\d+$");

	private boolean isValidID(String id) {
		if (id == null)
			return false;

		Matcher matcher = numericCheckPattern.matcher(id);
		return matcher.find();
	}

	public PedidoService getPedidoService() {
		return pedidoService;
	}

	public void setPedidoService(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

}
