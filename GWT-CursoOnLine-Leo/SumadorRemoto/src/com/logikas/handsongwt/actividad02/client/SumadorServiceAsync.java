package com.logikas.handsongwt.actividad02.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.logikas.handsongwt.actividad02.shared.Suma;

/**
 * The async counterpart of <code>SumaService</code>.
 */
public interface SumadorServiceAsync {
	public void sumar(Suma suma, AsyncCallback<Integer> callback)
			throws IllegalArgumentException;
}
