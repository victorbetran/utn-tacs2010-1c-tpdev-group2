package com.logikas.handsongwt.actividad01.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Sumador implements EntryPoint {

	@Override
	public void onModuleLoad() {
		
		//Instanciacion
		TextBox primerSumando = new TextBox();
		TextBox segundoSumando = new TextBox();
		Label resultado = new Label();
		Button boton = new Button("Sumar");
		
		
		
		
		
		//Bindeo de los componentes
		RootPanel.get("numero1").add(primerSumando);
		RootPanel.get("numero2").add(segundoSumando);
		RootPanel.get("resultado").add(resultado);
		RootPanel.get("sumar").add(boton);
		
		
	}

}
