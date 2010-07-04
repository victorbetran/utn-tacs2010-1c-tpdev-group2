package com.logikas.handsongwt.inventario.client.internacionalizacion;

import com.google.gwt.i18n.client.Constants;

public interface InventarioConstants extends Constants {
	@DefaultStringValue("Articulo")
	String articulo();

	@DefaultStringValue("Disponible")
	String disponible();

	@DefaultStringValue("Eliminar")
	String eliminar();

	@DefaultStringValue("Debe llenar el campo articulo")
	String llenarCampoArticulo();

	@DefaultStringValue("El articulo no puede contener espacios en blanco")
	String articuloConEspacios();
}