package org.utn.tacs.tp.group2.pedido;

import java.util.ArrayList;
import java.util.List;

import org.utn.tacs.tp.group2.dao.IDAsignator;
import org.utn.tacs.tp.group2.exceptions.PedidoEfectivizadoException;
import org.utn.tacs.tp.group2.exceptions.PedidoSinPiezasException;
import org.utn.tacs.tp.group2.pieza.Pieza;

public class Pedido {

	//********************************************
	//** ATRIBUTTES
	//********************************************
	private String id;
	private List<Pieza> piezas;
	private EstadoPedido estado;
	
	private volatile int hashCode;
	
	
	//********************************************
	//** PUBLIC CONSTRUCTOR
	//********************************************
	public Pedido() {
		this.id = IDAsignator.getInstance().getId();
		this.piezas = new ArrayList<Pieza>();
		this.estado = EstadoPedido.getEnCurso();
	}
	
	
	//********************************************
	//** PUBLIC METHODS
	//********************************************
	/**
	 * Cancela un pedido, cancelando sus piezas y cambiando su estado a <i>CANCELADO</i>.
	 */
	public void cancelar() {
		this.estado.setCancelado(this);
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
		this.estado.setEfectivo(this);
	}
	
	/**
	 * Agrega una pieza al pedido, controlando que pueda ser agregada y finalemente
	 * cambiando su estado a reservada.
	 * @param pieza: una pieza para agregar al pedido.
	 * @return Pedido: El objeto al que se le est� enviando el mensaje.
	 */
	public Pedido addPieza(Pieza pieza){
		pieza.setReservada();
		this.piezas.add(pieza);
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
	
	/**
	 * Indica el numero de piezas que ha sigo asignado al pedido.
	 */
	public int cantidadDePiezasAsignadas(){
		return this.piezas.size();
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
		//TODO: si alguna de las piezas es vendida y luego se dispara
		//excepcion, las piezas quedan en estado inconsistente.
		for(Pieza pieza : this.piezas){
			pieza.setVendida();
		}
	}
	
	
	//********************************************
	//** GETTERS AND SETTERS
	//********************************************
	/**
	 * Devuelve el conjunto de piezas que conforman el pedido.
	 */
	public List<Pieza> getPiezas() {
		List<Pieza> toReturn = new ArrayList<Pieza>();
		for (Pieza pieza : this.piezas) {
			toReturn.add(pieza);
		}
		return toReturn;
	}

	/**
	 * Devuelve el estado del pedido.
	 */
	public EstadoPedido getEstado() {
		return this.estado;
	}
	
	/**
	 * Devuelve el id del pedido.
	 */
	public String getId() {
		return this.id;
	}
	
	
	//********************************************
	//** OVERWRITTEN METHODS
	//********************************************
	@Override public String toString() {
		return this.id;
	}
	
	@Override public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}
		if(obj == this){
			return true;
		}
		if(!(obj instanceof Pedido)){ 
			return false;
		}
		Pedido pedido = (Pedido) obj;
		return this.id.equals(pedido.id);
	}
	
	@Override public int hashCode() {
		int result = this.hashCode;
		if(result == 0){
			result = 17;
			result = 31 * result * this.id.hashCode();
			this.hashCode = result;
		}
		return result;
	}

}
