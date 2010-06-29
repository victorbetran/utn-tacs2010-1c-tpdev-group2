package com.logikas.handsongwt.actividad04.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class GaleriaDeclarativaWidget extends Composite{

	//************************************
	//** MAGIC
	//************************************
	interface GaleriaDeclarativaUiBinder extends UiBinder<Widget, GaleriaDeclarativaWidget>{}
	private static GaleriaDeclarativaUiBinder uiBinder = GWT.create(GaleriaDeclarativaUiBinder.class);
	
	
	//************************************
	//** COMPONENTS
	//************************************
	@UiField(provided=true) protected FlexTable images;
	@UiField(provided=true) protected FormInput name;
	@UiField(provided=true) protected FormInput url;
	@UiField protected Label message;
	
	/**
	 * Modelo que contiene el listado de elementos de la tabla y la lógica para manipularlos.
	 */
	private GaleriaDeclarativaModel model;
	
	
	//************************************
	//** CONSTRUCTION
	//************************************
	public GaleriaDeclarativaWidget() {
		this.model = new GaleriaDeclarativaModel();
		this.createWidgets();
		initWidget(uiBinder.createAndBindUi(this));
	}

	/**
	 * Crea los componentes inicializador por el usuario.
	 */
	private void createWidgets() {
		this.createTableImages();
		this.createFormInputs();
	}

	/**
	 * Crea el formulario para ingresar los datos.
	 */
	private void createFormInputs() {
		this.name = new FormInput("Nombre");
		this.url = new FormInput("URL");
	}

	/**
	 * Crea la tabla donde residirán las imágenes con sus nombres.
	 */
	private void createTableImages() {
		this.images = new FlexTable();
		this.images.addTableListener(new ImageTableListener(this));
		this.images.setText(0, 0, "Nombre");
		this.images.setText(0, 1, "Imagen");
		this.images.getRowFormatter().addStyleName(0, "tablaImagenesHeader");
		this.images.getColumnFormatter().addStyleName(0, "tablaImagenesNombre");
		this.images.getColumnFormatter().addStyleName(0, "tablaImagenesImagen");
	}
	
	
	//************************************
	//** EVENTS HANDLERS
	//************************************
	/**
	 * Agrega un elemento a la tabla.
	 */
	@UiHandler("btnAccept")
	public void aceptar(ClickEvent event){
		try{
			ImageEntry newEntry = this.model.newImageEntry(this.name.getText(), this.url.getText());
			this.model.addImageEntry(newEntry);
			this.refreshPage();
		}
		catch (IllegalArgumentException e) {
			this.message.setText(e.getMessage());
		}
	}
	
	/**
	 * Selecciona una entrada de la tabla.
	 * @param row: número de fila que fue seleccionada
	 */
	public void select(int row) {
		this.loadImageEntry(this.model.getImageByPosition(row));
	}

	
	//************************************
	//** PRIVATE METHODS
	//************************************
	/**
	 * Refresca la pantalla.
	 */
	private void refreshPage(){
		this.refreshTableImages();
		this.cleanForm();
	}
	
	/**
	 * Refresca la tabla de imagenes.
	 */
	private void refreshTableImages() {
		int i = 1;
		for(ImageEntry iEntry : this.model.getImageEntries()){
			this.images.setText(i, 0, iEntry.getName().toUpperCase());
			this.images.setWidget(i, 1, new Image(iEntry.getUrl()));
			i++;
		}
	}
	
	/**
	 * Limpia el formulario de ingreso de datos.
	 */
	private void cleanForm(){
		this.name.setText("");
		this.url.setText("");
		this.message.setText("");
	}
	
	/**
	 * Carga los datos de la fila seleccionada.
	 */
	private void loadImageEntry(ImageEntry iEntry) {
		this.name.setText(iEntry.getName());
		this.url.setText(iEntry.getUrl());
	}


}
