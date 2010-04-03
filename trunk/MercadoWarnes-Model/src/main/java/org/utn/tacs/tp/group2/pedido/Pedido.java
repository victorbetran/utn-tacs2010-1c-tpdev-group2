package org.utn.tacs.tp.group2.pedido;

import java.util.ArrayList;
import java.util.List;

import org.utn.tacs.tp.group2.pieza.Pieza;

public class Pedido {

	//********************************************
	//** ATRIBUTTES
	//********************************************
	private List<Pieza> piezas;
	private EstadoPedido estado;
	
	
	//********************************************
	//** PUBLIC CONSTRUCTOR
	//********************************************
	public Pedido() {
		this.piezas = new ArrayList<Pieza>();
		this.estado = EstadoPedido.getEstadoEnCurso();
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
	 * Devuelve el Estado del Pedido.
	 */
	public EstadoPedido getEstado() {
		return this.estado;
	}
	
	/**
	 *  Efectiviza un pedido, vendiendo sus piezas y cambiando su estado a <i>EFECTIVO</i>.
	 */	
	public void efectivizar() {
		this.venderPiezas();
		this.estado.setEfectivo();
	}
	
	/**
	 * Agrega una pieza a la lista de piezas del pedido.
	 * @param pieza: una pieza para agregar al pedido.
	 */
	public void agregarPieza(Pieza pieza) {
		this.piezas.add(pieza);		
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
	
	//********************************************
	//** GETTERS AND SETTERS
	//********************************************
	public List<Pieza> getPiezas() {
		return piezas;
	}
	
	public void setPiezas(List<Pieza> piezas) {
		this.piezas = piezas;
	}



}
