package org.utn.tacs.tp.group2.service.pieza;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.utn.tacs.tp.group2.pieza.Auto;
import org.utn.tacs.tp.group2.pieza.Moneda;
import org.utn.tacs.tp.group2.pieza.Pieza;
import org.utn.tacs.tp.group2.service.implementation.PiezaDTO;

public class CreacionDePiezaDTOTest {

	private PiezaDTO piezaDto;
	private Pieza piezaModel;
	private Auto autoDePiezaModel;
	
	@Before
	public void setUp(){
		this.autoDePiezaModel = Auto.createAuto("patente", "modelo", 2010, new Date());
		this.piezaModel = new Pieza("CodigoPieza",10,Moneda.DOLAR);
		this.piezaModel.setAutoOrigen(this.autoDePiezaModel);
		this.piezaModel.setCategoria("UNA CATEGORIA");
		
		this.piezaDto = new PiezaDTO(piezaModel);
	}
	
	@Test
	public void validarCodigo(){
		Assert.assertEquals(this.piezaModel.getCodigo(), this.piezaDto.getCodigo());
	}

	@Test
	public void validarId(){
		Assert.assertEquals(this.piezaModel.getId().toString(), this.piezaDto.getId());		
	}
	
	@Test
	public void validarEstado(){
		Assert.assertEquals(this.piezaModel.getEstado().toString(), this.piezaDto.getEstado());
	}

	@Test
	public void validarCategoria(){
		Assert.assertEquals(this.piezaModel.getCategoria(), this.piezaDto.getCategoria());
	}

	@Test
	public void validarAutoOrigen(){
		Assert.assertEquals(this.piezaModel.getAutoOrigen().getId().toString(), this.piezaDto.getAutoOrigen());
	}
	
}
