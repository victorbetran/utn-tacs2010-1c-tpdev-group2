package com.logikas.handsongwt.actividad04.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * Representa una entrada en un formulario de ingreso de datos.
 */
public class FormInput extends Composite implements HasText{

	//************************************
	//** MAGIC
	//************************************
	interface FormInputUiBinder extends UiBinder<Widget, FormInput>{}
	private static FormInputUiBinder uiBinder = GWT.create(FormInputUiBinder.class);
	
	
	//************************************
	//** COMPONENTS
	//************************************
	@UiField protected TextBox text;
	@UiField protected Label label;
	
	
	//************************************
	//** CONSTRUCTION
	//************************************
	public FormInput(String valueLabel) {
		initWidget(uiBinder.createAndBindUi(this));
		this.label.setText(valueLabel);
	}


	//************************************
	//** PUBLIC METHODS
	//************************************
	@Override public String getText() {
		return this.text.getText();
	}

	@Override public void setText(String text) {
		this.text.setText(text);
	}
	
}
