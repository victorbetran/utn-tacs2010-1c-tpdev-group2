package org.utn.tacs.tp.group2.pieza;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.utn.tacs.tp.group2.persistence.PersistentObject;

@Entity
@Table(name = "AUTO")
public class Auto extends PersistentObject {

	// *************************************************************
	// * CONSTRUCTOR
	// *************************************************************
	
	public static Auto createAuto(String patente, String modelo, Integer anio, Date fechaDesguace){
		Auto toReturn = new Auto();
		
		toReturn.setPatente(patente)
				.setModelo(modelo)
				.setAnio(anio)
				.setFechaDeDesguace(fechaDesguace);
		
		return toReturn;
	}
	
	// ********************************************
	// ** ATRIBUTTES
	// ********************************************
	
	@Column(nullable = false, length = 6)
	private String patente;

	@Column(nullable = false)
	private String modelo;

	@Column(nullable = false)
	private Integer anio;

	@Column()
	private Date fechaDeDesguace;

	// ********************************************
	// ** OVERWRITTEN METHODS
	// ********************************************
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Auto)) {
			return false;
		}
		Auto auto = (Auto) obj;
		return auto.getId().equals(this.getId());
	}

	// ********************************************
	// ** GETTERS & SETTERS
	// ********************************************
	
	public String getPatente() {
		return this.patente;
	}

	public Auto setPatente(String patente) {
		this.patente = patente;
		return this;
	}

	public String getModelo() {
		return this.modelo;
	}

	public Auto setModelo(String modelo) {
		this.modelo = modelo;
		return this;
	}

	public Integer getAnio() {
		return this.anio;
	}

	public Auto setAnio(Integer anio) {
		this.anio = anio;
		return this;
	}

	public Date getFechaDeDesguace() {
		return this.fechaDeDesguace;
	}

	public Auto setFechaDeDesguace(Date fechaDeDesguace) {
		this.fechaDeDesguace = fechaDeDesguace;
		return this;
	}

}
