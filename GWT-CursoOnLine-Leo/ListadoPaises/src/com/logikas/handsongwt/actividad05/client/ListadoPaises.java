/*
 * Este archivo pertenece al curso "Hands On GWT" de Logikas Eduka
 *                    www.logikas.com - 2010
 */
package com.logikas.handsongwt.actividad05.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.RootPanel;
import com.logikas.handsongwt.actividad05.domain.Pais;

/**
 * @author eduka@logikas.com
 * 
 */
public class ListadoPaises implements EntryPoint {

  private static ListadoPaisesConstants constants = GWT.create(ListadoPaisesConstants.class);

  @Override
  public void onModuleLoad() {
    RootPanel.get().add(new ListadoPaisesWidget(Pais.getAll()));
    Document.get().setTitle(constants.tituloAplicacion());
  }
}
