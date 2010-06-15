package com.logikas.handsongwt.actividad02.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.logikas.handsongwt.actividad02.shared.NumeroNegativoException;
import com.logikas.handsongwt.actividad02.shared.Suma;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("sumar")
public interface SumadorService extends RemoteService {
	public int sumar(Suma suma) throws NumeroNegativoException;
}
 