package com.logikas.handsongwt.actividad04.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class GaleriaDeclarativa implements EntryPoint {

	@Override
	public void onModuleLoad() {
		RootPanel.get().add(new GaleriaDeclarativaWidget());
	}

}
