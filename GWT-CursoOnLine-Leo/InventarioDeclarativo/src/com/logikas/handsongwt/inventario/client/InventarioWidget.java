package com.logikas.handsongwt.inventario.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class InventarioWidget extends Composite{
	
	interface InventarioUiBinder extends UiBinder<Widget, InventarioWidget> {}

	private static InventarioUiBinder uiBinder = GWT.create(InventarioUiBinder.class);

	@UiField TextBox textoArticulo;
	@UiField CheckBox checkDisponible; 
	
	@UiHandler("botonAgregar")
	void agregar(ClickEvent event) {
		Window.alert("Click en Agregar");
	}
	
	public InventarioWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}