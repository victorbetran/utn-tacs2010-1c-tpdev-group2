/*
 * Este archivo pertenece al curso "Hands On GWT" de Logikas Eduka
 *                    www.logikas.com - 2010
 */
package com.logikas.handsongwt.actividad05.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.logikas.handsongwt.actividad05.domain.Pais;

/**
 * 
 * Widget que muestra una un listado de países por medio de una tabla paginada.
 * 
 * @author eduka@logikas
 *
 */
public class ListadoPaisesWidget extends Composite {

  private static ListadoPaisesWidgetUiBinder uiBinder = GWT.create(ListadoPaisesWidgetUiBinder.class);
  private static ListadoPaisesConstants constants = GWT.create(ListadoPaisesConstants.class);
  private static ListadoPaisesMessages messages = GWT.create(ListadoPaisesMessages.class);

  private static final int COL_NOMBRE = 0;
  private static final int COL_PREFIJO_TELEFONICO = 1;
  private static final int COL_CODIGO_ISO = 2;

  private final int filas = 4;

  private final int paginas;

  private int filaInicial = 0;

  private final Pais[] paises;

  @UiField
  FlexTable tablaPaises;

  @UiField
  Button atras;

  @UiField
  Button adelante;

  @UiField
  Label pagina;

  interface ListadoPaisesWidgetUiBinder extends
      UiBinder<Widget, ListadoPaisesWidget> {
  }

  /**
   * Instancia el widget a partir de una lista de países
   * 
   * @param paises lista de países
   */
  public ListadoPaisesWidget(Pais[] paises) {
    this.paises = paises;
    paginas = 1 + paises.length / filas;
    Widget root = uiBinder.createAndBindUi(this);
    inicializarTablaPaises();    
    moverPagina(0);
    initWidget(root);
  }

  /**
   * Inicializa la tabla de países
   */
  private void inicializarTablaPaises() {
    
    String headerNombre = constants.nombre();
    tablaPaises.setText(0, COL_NOMBRE, headerNombre);
    
    String headerPrefijo = constants.prefijo();
    tablaPaises.setText(0, COL_PREFIJO_TELEFONICO, headerPrefijo);
    
    String headerCodigoIso = constants.codigoIso();
    tablaPaises.setText(0, COL_CODIGO_ISO, headerCodigoIso);
  }

  /**
   * Avanza o retrocede una página de datos.
   * 
   * @param direccion Una dirección positiva avanza de página, mientras que
   * una dirección negativa retrocede de página
   */
  private void moverPagina(int direccion) {
    filaInicial += filas * direccion;
    int paginaActual = 1 + filaInicial / filas;
    
    String paginado = messages.paginas(paginaActual, paginas);    
    
    pagina.setText(paginado);
    
    for (int fila = 1; fila <= filas; fila++) {
      int filaDato = filaInicial + fila - 1;
      if (filaDato < paises.length) {
        Pais pais = paises[filaDato];
        tablaPaises.setText(fila, COL_NOMBRE, pais.getNombre());
        tablaPaises.setText(fila, COL_CODIGO_ISO, pais.getCodigoIso());
        
        String textoPrefijo = messages.area(pais.getPrefijoTelefonico());
        
        tablaPaises.setText(fila, COL_PREFIJO_TELEFONICO, textoPrefijo);
      } else {
        tablaPaises.removeRow(tablaPaises.getRowCount() - 1);
      }
    }
    atras.setEnabled(filaInicial > 0);
    adelante.setEnabled(paginaActual < paginas);
  }

  @UiHandler("atras")
  void atras(ClickEvent e) {
    moverPagina(-1);
  }

  @UiHandler("adelante")
  void adelante(ClickEvent e) {
    moverPagina(+1);
  }

}
