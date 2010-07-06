/*
 * Este archivo pertenece al curso "Hands On GWT" de Logikas Eduka
 *                    www.logikas.com - 2010
 */
package com.logikas.handsongwt.actividad05.domain;

/**
 * @author eduka@logikas.com
 */
public class Pais {

  private static final Pais[] PAISES = { 
    new Pais("Guatemala", "GT", 502),
    new Pais("Honduras", "HN", 504), 
    new Pais("Nicaragua", "NI", 505),
    new Pais("Costa Rica", "CR", 506),
    new Pais("Panamá", "PA", 507),
    new Pais("Haití", "HT", 509), 
    new Pais("Perú", "PE", 51),
    new Pais("México", "MX", 52), 
    new Pais("Cuba", "CU", 53),
    new Pais("Argentina", "AR", 54), 
    new Pais("España", "ES", 34),
    new Pais("Chile", "CL", 56),
    new Pais("Colombia", "CO", 57),
    new Pais("Venezuela", "VE", 58),
    new Pais("Bolivia", "BO", 591),
    new Pais("Ecuador", "EC", 593),
    new Pais("Paraguay", "PY", 595),
    new Pais("Uruguay", "UY", 598)
  };

  private String nombre;

  private int prefijoTelefonico;

  private String codigoIso;

  public Pais(String nombre, String codigoIso, int prefijoTelefonico) {
    this.nombre = nombre;
    this.prefijoTelefonico = prefijoTelefonico;
    this.codigoIso = codigoIso;
  }

  public static Pais[] getAll() {
    return PAISES;
  }

  public String getNombre() {
    return nombre;
  }

  public int getPrefijoTelefonico() {
    return prefijoTelefonico;
  }

  public String getCodigoIso() {
    return codigoIso;
  }
}
