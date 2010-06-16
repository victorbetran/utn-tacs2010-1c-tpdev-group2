package com.logikas.handsongwt.inventario.client;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

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
	//* CONSTRUCTOR
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

}
