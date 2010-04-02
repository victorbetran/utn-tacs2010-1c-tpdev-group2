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
		this.estado = new EstadoPedido();
	}
	
	
	//********************************************
	//** PUBLIC METHODS
	//********************************************
	/**Cancela un pedido, cancelando sus piezas y cambiando su estado a <i>CANCELADO</i>.*/
	public void cancelar() {
		this.estado.setCancelado();
		this.cancelarPiezas();
	}

	/**Devuelve el Estado del Pedido.*/
	public EstadoPedido getEstado() {
		return this.estado;
	}
	
	/**Efectiviza un pedido, vendiendo sus piezas y cambiando su estado a <i>EFECTIVO</i>.*/	
	public void efectivizar() {
		// TODO: Determinar que hacer con las piezas cuando el pedido se efectiviza
		this.estado.setEfectivo();
	}

	
	//********************************************
	//** PRIVATE METHOD
	//********************************************
	/**Setea el estado de las piezas a Disponible.*/
	private void cancelarPiezas() {
		//TODO: implementar el cancelado de las piezas.
		this.piezas.clear();
	}




}
