package com.logikas.handsongwt.actividad01.client;

import java.util.ArrayList;
import java.util.List;

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
		resultado.setVisible(false);
		Button boton = new Button("Sumar");
		
		//Binding Object-Tag
		RootPanel.get("sumando1").add(primerSumando);
		RootPanel.get("sumando2").add(segundoSumando);
		RootPanel.get("suma").add(resultado);
		RootPanel.get("sumar").add(boton);

		//Comportamiento
		boton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event){
				try{
					Suma suma = new Suma();
					int num1 = Integer.parseInt(primerSumando.getText());
					int num2 = Integer.parseInt(segundoSumando.getText());
					resultado.setText(suma.addSumando(num1).addSumando(num2).execute().toString());
				}
				catch(NumberFormatException e){
					resultado.setText("Error en los valores de entrada");
				}
				resultado.setVisible(true);
			}
		});
		
	}
}

class Suma {
	
	private List<Integer> sumandos;
	
	public Suma() {
		this.sumandos = new ArrayList<Integer>();
	}
	
	public Suma addSumando(int sumando){
		this.sumandos.add(Integer.valueOf(sumando));
		return this;
	}
	
	public Integer execute(){
		Integer result = Integer.valueOf(0);
		for(Integer sumando : this.sumandos){
			result = result.intValue() + sumando.intValue();
		}
		return result;
	}
}

