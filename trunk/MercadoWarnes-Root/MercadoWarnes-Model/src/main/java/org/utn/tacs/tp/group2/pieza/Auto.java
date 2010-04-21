package org.utn.tacs.tp.group2.pieza;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.utn.tacs.tp.group2.utils.PersistentObject;

@Entity
@Table(name = "AUTO")
public class Auto extends PersistentObject {

	// ********************************************
	// ** ATRIBUTTES
	// ********************************************
	@OneToMany(mappedBy = "autoOrigen")
	private List<Pieza> piezas;

	@Column(nullable = false, length = 6)
	private String patente;

	@Column(nullable = false)
	private String modelo;

	@Column(nullable = false)
	private String anio;

	@Column(nullable = false)
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
		return auto.patente.equals(this.patente);
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

	public String getAnio() {
		return this.anio;
	}

	public Auto setAnio(String anio) {
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

	public List<Pieza> getPiezas() {
		return this.piezas;
	}

	public Auto setPiezas(List<Pieza> piezas) {
		this.piezas = piezas;
		return this;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

}
