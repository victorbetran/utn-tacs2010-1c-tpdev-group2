package com.logikas.handsongwt.inventario.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Inventario implements EntryPoint {

	public void onModuleLoad() {
		
		RootPanel.get().add(new InventarioWidget());
	}
}
