package com.logikas.handsongwt.inventario.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.logikas.handsongwt.inventario.client.internacionalizacion.InventarioConstants;
import com.logikas.handsongwt.inventario.client.internacionalizacion.InventarioMessages;

public class InventarioWidget extends Composite {

	// Posición de la columna Artículo
	private static final int COL_ARTICULO = 0;

	// Posición de la columna Disponible
	private static final int COL_DISPONIBLE = 1;

	// Posición de la columna Eliminar
	private static final int COL_ELIMINAR = 2;

	// Versión horizontal alternativa
	@UiTemplate("HorizontalInventarioWidget.ui.xml")
	interface HorizontalInventarioWidgetUiBinder extends UiBinder<Widget, InventarioWidget> {}
	private static final HorizontalInventarioWidgetUiBinder horizontalUiBinder = GWT.create(HorizontalInventarioWidgetUiBinder.class);
	
	interface InventarioUiBinder extends UiBinder<Widget, InventarioWidget> {}
	private static final InventarioUiBinder uiBinder = GWT.create(InventarioUiBinder.class);

	private final InventarioConstants constants = GWT.create(InventarioConstants.class);
	private final InventarioMessages messages = GWT.create(InventarioMessages.class);
	
	private ArrayList<String> articulos = new ArrayList<String>();

	@UiField
	TextBox textoArticulo;

	@UiField
	CheckBox checkDisponible;

	@UiField(provided = true)
	FlexTable tablaInventario;

	private void crearTablaInventario() {

		tablaInventario = new FlexTable();

		// Establecer encabezados
		tablaInventario.setText(0, COL_ARTICULO, constants.articulo());
		tablaInventario.setText(0, COL_DISPONIBLE, constants.disponible());
		tablaInventario.setText(0, COL_ELIMINAR, constants.eliminar());

		tablaInventario.getRowFormatter().addStyleName(0,
				"tablaInventarioHeader");
		tablaInventario.addStyleName("tablaInventario");
	}

	@UiHandler("botonAgregar")
	void agregar(ClickEvent event) {
		agregarItem();
	}

	@UiHandler("textoArticulo")
	void agregar(KeyPressEvent event) {

		// Si se presiona la tecla Enter
		if (event.getCharCode() == KeyCodes.KEY_ENTER) {
			agregarItem();
		}
	}

	// Por default se crea una versión vertical
	public InventarioWidget() {
		this(uiBinder);
	}
	
	private InventarioWidget(UiBinder<Widget, InventarioWidget> binder) {
		crearTablaInventario();
		initWidget(binder.createAndBindUi(this));
	}
	
	public static InventarioWidget crearHorizontal() {
		return new InventarioWidget(horizontalUiBinder);
	}

	private boolean validarArticulo(String articulo) {

		if (articulo.isEmpty()) {
			Window.alert(constants.llenarCampoArticulo());
			return false;
		}

		if (articulo.contains(" ")) {
			Window.alert(constants.articuloConEspacios());
			return false;
		}

		if (articulos.contains(articulo)) {
			Window.alert(messages.articuloYaRegistrado(articulo));
			return false;
		}

		return true;
	}

	private void eliminarItem(String articulo) {

		int fila = articulos.indexOf(articulo);
		articulos.remove(fila);

		// La fila 0 (cero) es de headers, por eso se suma 1 (uno)
		tablaInventario.removeRow(fila + 1);
	}

	private Widget crearWidgetDisponible(boolean disponible) {
		String urlRelativa = disponible ? "images/si.png" : "images/no.png";
		String url = GWT.getHostPageBaseURL() + urlRelativa;
		return new Image(url);
	}

	private void agregarItem() {

		final String articulo = textoArticulo.getValue();

		// Si el articulo no es valido no se hace nada
		if (!validarArticulo(articulo)) {
			return;
		}

		// Limpiar Campo Articulo
		textoArticulo.setValue("");

		int fila = tablaInventario.getRowCount();

		tablaInventario.getCellFormatter().addStyleName(fila, COL_ARTICULO,
				"tablaInventarioArticulo");
		tablaInventario.getCellFormatter().addStyleName(fila, COL_DISPONIBLE,
				"tablaInventarioDisponible");
		tablaInventario.getCellFormatter().addStyleName(fila, COL_ELIMINAR,
				"tablaInventarioEliminar");

		Label labelArticulo = new Label();
		labelArticulo.setText(articulo);
		tablaInventario.setWidget(fila, COL_ARTICULO, labelArticulo);

		boolean disponible = checkDisponible.getValue();

		// Limpiar Campo Disponible
		checkDisponible.setValue(false);

		Widget widgetDisponible = crearWidgetDisponible(disponible);

		tablaInventario.setWidget(fila, COL_DISPONIBLE, widgetDisponible);

		Button botonEliminar = new Button("X");
		botonEliminar.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eliminarItem(articulo);
			}
		});

		tablaInventario.setWidget(fila, COL_ELIMINAR, botonEliminar);

		textoArticulo.setFocus(true);

		articulos.add(articulo);
	}

}
