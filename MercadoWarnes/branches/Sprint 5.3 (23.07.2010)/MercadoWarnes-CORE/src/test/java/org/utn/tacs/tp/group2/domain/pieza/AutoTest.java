package org.utn.tacs.tp.group2.domain.pieza;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.utn.tacs.tp.group2.pieza.Auto;

public class AutoTest {

	private Auto auto;
	private String patente;
	private String modelo;
	private Integer anio;
	private Date fechaDesguace;
	
	@Before
	public void setUp(){
		this.patente = "TTR-243";
		this.modelo = "Robotico";
		this.anio = 2019;
		this.fechaDesguace = new Date();
		
		this.auto = Auto.createAuto(patente, modelo, anio, fechaDesguace);
	}
	
	@Test
	public void validarPatente() {
		Assert.assertEquals(this.patente, this.auto.getPatente());
	}
	
	@Test
	public void validarModelo() {
		Assert.assertEquals(this.modelo, this.auto.getModelo());
	}
	
	@Test
	public void validarAnio() {
		Assert.assertEquals(this.anio, this.auto.getAnio());
	}
	
	@Test
	public void validarFechaDesguace() {
		Assert.assertEquals(this.fechaDesguace, this.auto.getFechaDeDesguace());
	}
	
}
