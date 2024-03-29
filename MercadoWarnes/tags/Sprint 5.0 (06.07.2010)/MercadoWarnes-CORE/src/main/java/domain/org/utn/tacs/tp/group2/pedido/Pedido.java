package org.utn.tacs.tp.group2.pedido;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.utn.tacs.tp.group2.exceptions.pedido.CancelacionDePedidoException;
import org.utn.tacs.tp.group2.exceptions.pedido.EfectivizacionDePedidoException;
import org.utn.tacs.tp.group2.exceptions.pedido.PedidoSinPiezasException;
import org.utn.tacs.tp.group2.exceptions.pieza.PiezaException;
import org.utn.tacs.tp.group2.log.TheLogger;
import org.utn.tacs.tp.group2.persistence.PersistentObject;
import org.utn.tacs.tp.group2.pieza.Pieza;

@Entity
@Table(name = "PEDIDO")
public class Pedido extends PersistentObject{

	// ********************************************
	// ** ATRIBUTTES
	// ********************************************
	/**
	 * Cuando se guarda o actualiza un pedido le pega a las piezas, pero si se borra, no borra a las
	 * piezas asosiadas a el mismo
	 */
	@OneToMany(cascade = CascadeType.ALL)
	private List<Pieza> piezas;

	@Embedded
	private EstadoPedido estado;

	// ********************************************
	// ** PUBLIC CONSTRUCTOR
	// ********************************************
	
	public static Pedido create(){
		Pedido toReturn = new Pedido();
		
		toReturn.piezas = new ArrayList<Pieza>();
		toReturn.estado = EstadoPedido.getEnCurso();
		
		TheLogger.getConsoleLogger().debug("Se crea el pedido:{}", toReturn);
		
		return toReturn;
	}
	
	public Pedido() {}

	// ********************************************
	// ** PUBLIC METHODS
	// ********************************************
	/**
	 * Cancela un pedido, cancelando sus piezas y cambiando su estado a <i>CANCELADO</i>.
	 */

	public void cancelar() {
		try {
			this.disponibilizarPiezas();
			this.estado.gotoCancelado(this);
			TheLogger.cambioDeEstadoLog(this);
		} catch (PiezaException e) {
			TheLogger.getConsoleLogger().debug("No se pudo cancelar el pedido:{}", this);
			throw new CancelacionDePedidoException(this, e);
		}
	}
	
	/**
	 * Efectiviza un pedido, vendiendo sus piezas y cambiando su estado a <i>EFECTIVO</i>.
	 */
	public void efectivizar() {
		try {
			this.venderPiezas();
			this.estado.gotoEfectivo(this);
			TheLogger.cambioDeEstadoLog(this);
			this.notifyEfectivizacion();
		} catch (PiezaException e) {
			throw new EfectivizacionDePedidoException(this, e);
		}
	}

	/**
	 * Agrega una pieza al pedido, controlando que pueda ser agregada y finalemente cambiando su
	 * estado a reservada.
	 * 
	 * @param pieza
	 *            : una pieza para agregar al pedido.
	 * @return Pedido: El objeto al que se le est� enviando el mensaje.
	 */
	public Pedido addPieza(Pieza pieza) {
		pieza.reservar();
		this.piezas.add(pieza);
		TheLogger.getConsoleLogger().debug("Se agrego la pieza:{} al pedido:{}", pieza, this);
		return this;
	}

	/**
	 * Agrega un conjunto de pieza al pedido.
	 */
	public void addPiezas(List<Pieza> piezas) {
		for (Pieza pieza : piezas) {
			TheLogger.getConsoleLogger().debug("Se agrego la pieza:{} al pedido:{}", pieza, this);
			this.addPieza(pieza);
		}
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
	 * 
	 * @param pieza
	 *            : pieza en cuesti�n.
	 * @return
	 */
	public boolean contienePieza(Pieza pieza) {
		return this.piezas.contains(pieza);
	}

	/**
	 * Indica si el pedido est� conteniendo alguna pieza.
	 * 
	 * @return
	 */
	public boolean tienePiezas() {
		return !this.piezas.isEmpty();
	}

	/**
	 * Indica el numero de piezas que ha sigo asignado al pedido.
	 */
	public int cantidadDePiezasAsignadas() {
		return this.piezas.size();
	}

	//********************************************
	//** OBSERVING SERVICES
	//********************************************
	
	@Transient
	private List<EfectivizacionPedidosObserver> efectvizacionObserver = new ArrayList<EfectivizacionPedidosObserver>();
	
	public void acceptEfectivizacionObserver(EfectivizacionPedidosObserver obs){
		this.efectvizacionObserver.add(obs);
	}
	
	private void notifyEfectivizacion(){
		for (EfectivizacionPedidosObserver obs : this.efectvizacionObserver) {
			obs.pedidoEfectivizado(this);
		}
	}
	
	// ********************************************
	// ** PRIVATE METHOD
	// ********************************************
	/**
	 * Setea el estado de las piezas a Disponible.
	 */
	private void disponibilizarPiezas() {
		for (Pieza pieza : this.piezas) {
			pieza.disponibilizar();
		}
		this.piezas.clear();
	}

	/**
	 * Setea el estado de las piezas a Vendidas.
	 */
	private void venderPiezas() {
		if (this.piezas.isEmpty())
			throw new PedidoSinPiezasException(this);
		// TODO: si alguna de las piezas es vendida y luego se dispara
		// excepcion, las piezas quedan en estado inconsistente.
		for (Pieza pieza : this.piezas) {
			pieza.vender();
		}
	}

	// ********************************************
	// ** GETTERS AND SETTERS
	// ********************************************
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
	
	public Pedido setEstado(EstadoPedido estado) {
		this.estado = estado;
		return this;
	}

	// ********************************************
	// ** OVERWRITTEN METHODS
	// ********************************************
	@Override
	public String toString() {
		return this.getId().toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Pedido)) {
			return false;
		}

		Pedido pedido = (Pedido) obj;
		return this.getId().equals(pedido.getId());
	}

	

}
