package com.logikas.handsongwt.actividad02.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.logikas.handsongwt.actividad02.shared.Suma;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SumadorRemoto implements EntryPoint {

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final SumadorServiceAsync sumaService = GWT.create(SumadorService.class);

	//****************************
	//* VISUAL COMPONENTS
	//****************************
	private final TextBox	primerSumando	= new TextBox();
	private final TextBox	segundoSumando	= new TextBox();
	private final Label		resultadoLabel	= new Label("Resultado:");
	private final Label		resultado		= new Label();
	private final Button 	boton 			= new Button("Sumar");
	
	/**
	 * This is the entry point method.
	 */
	@Override
	public void onModuleLoad() {
		
		this.showResult(false);
		this.tagsComponentsBinding();

		this.boton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event){
				try{
					Suma suma = new Suma();
					suma.addSumando(Integer.parseInt(primerSumando.getText()));
					suma.addSumando(Integer.parseInt(segundoSumando.getText()));
					enableDataEntry(false);
					sumaService.sumar(suma,
						new AsyncCallback<Integer>() {
							public void onFailure(Throwable caught) {
								resultado.setText("Error en la Red");
							}
							public void onSuccess(Integer result) {
								resultado.setText(result.toString());
							}
						});
				}
				catch(NumberFormatException e){
					resultado.setText("Error de numero negativo");
				}
				showResult(true);
				enableDataEntry(true);
			}
		});
	}

	/**
	 * Habilita o deshabilita los campos de ingreso de datos.
	 */
	private void enableDataEntry(boolean isEnable) {
		this.boton.setEnabled(isEnable);
		this.primerSumando.setEnabled(isEnable);
		this.segundoSumando.setEnabled(isEnable);
	}
	
	/**
	 * Realiza el binding entre los tags HTML y los componentes visuales.
	 */
	private void tagsComponentsBinding() {
		RootPanel.get("sumando1").add(primerSumando);
		RootPanel.get("sumando2").add(segundoSumando);
		RootPanel.get("resultadoLabel").add(resultadoLabel);
		RootPanel.get("suma").add(resultado);
		RootPanel.get("sumar").add(boton);
	}

	/**
	 * Oculta los componentes que forman el resultado. 
	 */
	private void showResult(boolean isVisible) {
		this.resultadoLabel.setVisible(isVisible);
		this.resultado.setVisible(isVisible);
	}
		
}
