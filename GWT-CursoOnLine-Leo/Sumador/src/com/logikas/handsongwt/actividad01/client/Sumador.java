package com.logikas.handsongwt.actividad01.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
		
		//Instanciacion de componentes
		final TextBox primerSumando = new TextBox();
		final TextBox segundoSumando = new TextBox();
		final Label resultado = new Label();
		
		Button boton = new Button("Sumar");
		boton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Integer num1 = Integer.valueOf(primerSumando.getText());
				Integer num2 = Integer.valueOf(segundoSumando.getText());
				resultado.setText(Integer.valueOf(num1.intValue() + num2.intValue()).toString());
			}
		});
		
		
		
		//Bindeo de los componentes al HTML
		RootPanel.get("numero1").add(primerSumando);
		RootPanel.get("numero2").add(segundoSumando);
		RootPanel.get("resultado").add(resultado);
		RootPanel.get("sumar").add(boton);
		
		
	}

}
