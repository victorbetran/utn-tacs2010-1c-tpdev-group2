package com.logikas.handsongwt.inventario.client;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class InventarioWidget extends Composite{
	
	//**********************************
	//* COMPONENTES
	//**********************************
	private VerticalPanel panelPrincipal;
	private FlexTable tablaInventario;
	private HorizontalPanel panelAgregar;
	private TextBox textoArticulo;
	private CheckBox checkDisponible;
	private Button botonAgregar;
	
	//**********************************
	//* COLUMNAS
	//**********************************
	// Posición de la columna Artículo
	private static final int COL_ARTICULO = 0;
	// Posición de la columna Disponible
	private static final int COL_DISPONIBLE = 1;
	// Posición de la columna Eliminar
	private static final int COL_ELIMINAR = 2;
	
	/**
	 * Lista de Artículos
	 */
	private ArrayList<String> articulos = new ArrayList<String>();
	
	
	//**********************************
	//* METODOS DE CONSTRUCCION
	//**********************************
	public InventarioWidget() {
		
		this.panelPrincipal = new VerticalPanel();
		
		// Establecer panelPrincipal como widget asociado al Composite
		initWidget(this.panelPrincipal);
		
		// Crear la tabla de Inventario
		this.crearTablaInventario();
		
		// Crear panel de carga
		this.crearPanelAgregar();
		
	}

	/**
	 * 
	 */
	private void crearPanelAgregar() {
		this.panelAgregar = new HorizontalPanel();
		this.textoArticulo = new TextBox();
		this.panelAgregar.add(this.textoArticulo);
		this.checkDisponible = new CheckBox();
		this.panelAgregar.add(this.checkDisponible);
		this.botonAgregar = new Button("Agregar");
		this.panelAgregar.add(this.botonAgregar);
		this.panelPrincipal.add(this.panelAgregar);
		
		this.botonAgregar.addClickHandler(new ClickHandler(){ 
			public void onClick(ClickEvent event) { 
				agregarItem(); 
			} 
		});
	}

	/**
	 * 
	 */
	private void crearTablaInventario() {
		this.tablaInventario = new FlexTable();
		// Establecer encabezados
		this.tablaInventario.setText(0, COL_ARTICULO, "Articulo");
		this.tablaInventario.setText(0, COL_DISPONIBLE, "Disponible");
		this.tablaInventario.setText(0, COL_ELIMINAR, "Eliminar");
		// Agregar Tabla de Inventario al Panel Principal
		this.panelPrincipal.add(this.tablaInventario);
	}

	
	//**********************************
	//* METODOS DE USO
	//**********************************
	/**
	 * 
	 */
	private void agregarItem() {
		
		// Obtener articulo cargado
		final String articulo = this.textoArticulo.getValue();
		
		// Limpiar Campo Articulo
		textoArticulo.setValue("");
		
		// Obtener cantidad de filas en tabla
		int fila = this.tablaInventario.getRowCount();
		
		// Cargar celda de artículo
		Label labelArticulo = new Label();
		labelArticulo.setText(articulo);
		
		this.tablaInventario.setWidget(fila, COL_ARTICULO, labelArticulo);
		
		// Obtener valor disponible
		boolean disponible = this.checkDisponible.getValue();
		Widget widgetDisponible = crearWidgetDisponible(disponible);
		this.tablaInventario.setWidget(fila, COL_DISPONIBLE, widgetDisponible);
		Button botonEliminar = new Button("X");
		botonEliminar.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				eliminarItem(articulo);
			}
		});
		
		this.tablaInventario.setWidget(fila, COL_ELIMINAR, botonEliminar);
		
		// Agregar artículos al registro interno
		this.articulos.add(articulo);
	}

	private Widget crearWidgetDisponible(boolean disponible) {
		String texto = disponible? "Si": "No";
		Label label = new Label();
		label.setText(texto);
		return label;
	}
	
	private void eliminarItem(String articulo) {
		int fila = this.articulos.indexOf(articulo);
		this.articulos.remove(fila);
		
		// La fila 0 (cero) es de headers, por eso se suma 1 (uno)
		this.tablaInventario.removeRow(fila + 1);
		}
}
