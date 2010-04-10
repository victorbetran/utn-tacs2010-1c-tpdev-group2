package org.utn.tacs.tp.group2.pedido;

import java.util.ArrayList;
import java.util.List;

import org.utn.tacs.tp.group2.exceptions.InclusionDePiezaNoPermitidaException;
import org.utn.tacs.tp.group2.exceptions.PedidoEfectivizadoException;
import org.utn.tacs.tp.group2.exceptions.PedidoSinPiezasException;
import org.utn.tacs.tp.group2.pieza.Pieza;

public class Pedido {

	//********************************************
	//** ATRIBUTTES
	//********************************************
	private String id = "Pedido";
	private List<Pieza> piezas;
	private EstadoPedido estado;
	
	
	//********************************************
	//** PUBLIC CONSTRUCTOR
	//********************************************
	public Pedido() {
		this.piezas = new ArrayList<Pieza>();
		this.estado = EstadoPedido.getEstadoEnCursoFor(this);
	}
	
	
	//********************************************
	//** PUBLIC METHODS
	//********************************************
	/**
	 * Cancela un pedido, cancelando sus piezas y cambiando su estado a <i>CANCELADO</i>.
	 */
	public void cancelar() {
		this.estado.setCancelado();
		this.cancelarPiezas();
	}

	/**
	 *  Efectiviza un pedido, vendiendo sus piezas y cambiando su estado a <i>EFECTIVO</i>.
	 */	
	public void efectivizar() {
		if (this.piezas.isEmpty())
			throw new PedidoSinPiezasException(this);
		if (this.isEfectivo())
			throw new PedidoEfectivizadoException(this);
		this.venderPiezas();
		this.estado.setEfectivo();
	}
	
	/**
	 * Agrega una pieza al pedido, controlando que pueda ser agregada y finalemente
	 * cambiando su estado a reservada.
	 * @param pieza: una pieza para agregar al pedido.
	 * @return Pedido: El objeto al que se le est� enviando el mensaje.
	 */
	public Pedido addPieza(Pieza pieza){
		controlarDisponibilidadDePieza(pieza);
		this.piezas.add(pieza);
		pieza.setReservada();
		return this;
	}
	
	/**
	 * Agrega un conjunto de pieza al pedido.
	 */
	public void addPiezas(List<Pieza> piezas) {
		for(Pieza pieza: piezas)
			this.addPieza(pieza);
	}
	
	/**
	 * Determina si el Pedido fue Cancelado.
	 */
	public boolean isEfectivo() {
		return this.estado.isEfectivo();
	}
	
	/**
	 * Determina si el Pedido fue Cancelado.
	 */
	public boolean isCancelado() {
		return this.estado.isCancelado();
	}
	
	/**
	 * Determina si el Pedido fue Cancelado.
	 */
	public boolean isEnCurso() {
		return this.estado.isEnCurso();
	}
	
	
	//********************************************
	//** PRIVATE METHOD
	//********************************************
	/**
	 * Setea el estado de las piezas a Disponible.
	 */
	private void cancelarPiezas() {
		for(Pieza pieza : this.piezas){
			pieza.setDisponible();
		}
		this.piezas.clear();
	}
	
	/**
	 * Setea el estado de las piezas a Vendidas.
	 */
	private void venderPiezas() {
		for(Pieza pieza : this.piezas){
			pieza.setVendida();
		}
	}
	
	/**
	 * Si el estado de la pieza no permite ser inclu�da en un pedido, lanzar� una excepci�n.
	 */
	private void controlarDisponibilidadDePieza(Pieza pieza) {
		if(!pieza.isDisponible()){
			throw new InclusionDePiezaNoPermitidaException(pieza);
		}
		
	}
	
	/**
	 * Indica si el pedido contiene a una pieza.
	 * @param pieza: pieza en cuesti�n.
	 * @return
	 */
	public boolean contienePieza(Pieza pieza){
		return this.piezas.contains(pieza);
	}
	
	/**
	 * Indica si el pedido est� conteniendo alguna pieza.
	 * @return
	 */
	public boolean tienePiezas(){
		return !this.piezas.isEmpty();
	}
	
	public int cantidadDePiezasAsignadas(){
		return this.piezas.size();
	}
	
	//********************************************
	//** GETTERS AND SETTERS
	//********************************************
	/**
	 * Devuelve el conjunto de piezas que conforman el pedido.
	 * @return
	 */
	public List<Pieza> getPiezas() {
		List<Pieza> toReturn = new ArrayList<Pieza>();
		for (Pieza pieza : this.piezas) {
			toReturn.add(pieza);
		}
		return toReturn;
	}

	
	//********************************************
	//** OVERWRITTEN METHODS
	//********************************************
	@Override public String toString() {
		return this.id;
	}

}
