package com.logikas.handsongwt.actividad02.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.logikas.handsongwt.actividad02.client.SumadorService;
import com.logikas.handsongwt.actividad02.shared.NumeroNegativoException;
import com.logikas.handsongwt.actividad02.shared.Suma;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class SumadorServiceImpl extends RemoteServiceServlet implements SumadorService {

	@Override
	public int sumar(Suma suma) throws NumeroNegativoException {
		suma.validate(); 
		return suma.execute();
	}
}
