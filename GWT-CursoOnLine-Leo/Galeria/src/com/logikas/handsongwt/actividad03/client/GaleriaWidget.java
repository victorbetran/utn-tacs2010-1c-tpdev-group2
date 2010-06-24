package com.logikas.handsongwt.actividad03.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class GaleriaWidget extends Composite{

	private GaleriaModel model = new GaleriaModel();
	private FlexTable imageTable;
	private Label errorMessage;
	private TextBox txtName;
	private TextBox txtURL;
	
	
	public GaleriaWidget() {
		VerticalPanel principalPanel = new VerticalPanel();
		this.createImageVisor(principalPanel);
		this.createImageFormInput(principalPanel);
		this.initWidget(principalPanel);
		principalPanel.add(this.errorMessage = new Label());
	}

	/**
	 * 
	 */
	private void createImageVisor(VerticalPanel principalPanel) {
		this.imageTable = new FlexTable();
		this.imageTable.setText(0, 0, "Nombre");
		this.imageTable.setText(0, 1, "Imagen");
		this.imageTable.getRowFormatter().addStyleName(0, "tablaImagenesHeader");
		this.imageTable.getColumnFormatter().addStyleName(0, "tablaImagenesNombre");
		this.imageTable.getColumnFormatter().addStyleName(0, "tablaImagenesImagen");
		principalPanel.add(this.imageTable);
	}
	
	/**
	 * 
	 */
	private void createImageFormInput(VerticalPanel principalPanel) {
		VerticalPanel form = new VerticalPanel();
		HorizontalPanel formLineName = new HorizontalPanel();
		HorizontalPanel formLineURL = new HorizontalPanel();
		HorizontalPanel formLineAgregar = new HorizontalPanel();
		
		Label lbName = new Label("Nombre");
		this.txtName = new TextBox();
		formLineName.add(lbName);
		formLineName.add(this.txtName);
		form.add(formLineName);
		
		Label lbURL = new Label("URL");
		this.txtURL = new TextBox();
		formLineURL.add(lbURL);
		formLineURL.add(this.txtURL);
		form.add(formLineURL);
		
		Button btnAgregar = new Button("Agregar");
		btnAgregar.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addImageEntry(txtName.getText(), txtURL.getText());
			}
		});
		
		formLineAgregar.add(btnAgregar);
		form.add(formLineAgregar);
		principalPanel.add(form);
	}

	private void refreshPage() {
		this.errorMessage.setText("");
		this.txtName.setText("");
		this.txtURL.setText("");
		int fila = 1;
		for(ImageEntry image : model.getImageEntries()){
			this.imageTable.setText(fila, 0, image.getName());
			final int row = fila;
			final Image newImage = new Image(image.getURL());
			newImage.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					selectImageEntry(imageTable.getText(row, 0));
				}
			});
			this.imageTable.setWidget(fila, 1, newImage);
			fila += 1;
		}
	}
	
	private void addImageEntry(String name, String url) {
		try{
			ImageEntry entry = new ImageEntry(name, url);
			this.model.addImageEntry(entry);
			refreshPage();
		}
		catch (IllegalArgumentException e) {
			errorMessage.setText(e.getMessage());
		}
		
	}
	
	private void selectImageEntry(String imageName) {
		ImageEntry entry = model.getImageByName(imageName);
		txtName.setText(entry.getName());
		txtURL.setText(entry.getURL());
	}
	
}



