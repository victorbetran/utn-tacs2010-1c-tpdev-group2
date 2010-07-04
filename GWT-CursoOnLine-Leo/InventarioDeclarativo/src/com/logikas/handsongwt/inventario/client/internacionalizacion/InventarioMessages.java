package com.logikas.handsongwt.inventario.client.internacionalizacion;

import com.google.gwt.i18n.client.Messages;

public interface InventarioMessages extends Messages {
	@DefaultMessage("El articulo {0} ya fue registrado")
	String articuloYaRegistrado(String articulo);
}