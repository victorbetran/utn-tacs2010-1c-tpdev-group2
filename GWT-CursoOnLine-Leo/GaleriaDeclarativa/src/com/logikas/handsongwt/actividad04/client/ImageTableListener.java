package com.logikas.handsongwt.actividad04.client;

import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;

public class ImageTableListener implements TableListener {

	private GaleriaDeclarativaWidget view;
	
	public ImageTableListener(GaleriaDeclarativaWidget galeriaDeclarativaWidget) {
		this.view = galeriaDeclarativaWidget;
	}

	@Override
	public void onCellClicked(SourcesTableEvents sender, int row, int cell) {
		this.view.select(row - 1);
	}

}
