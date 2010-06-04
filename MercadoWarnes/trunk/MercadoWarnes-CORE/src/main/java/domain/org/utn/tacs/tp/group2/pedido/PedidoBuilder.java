package org.utn.tacs.tp.group2.pedido;

import org.utn.tacs.tp.group2.log.TheLogger;
import org.utn.tacs.tp.group2.pieza.Pieza;

public class PedidoBuilder {

	private Pedido pedido;
	
	public PedidoBuilder()
	{
		this.pedido=new Pedido();
		this.pedido.inicializarPedido();		
	}
	
	
	public PedidoBuilder addPieza(Pieza pieza)
	{
		this.pedido.addPieza(pieza);
		return this;
	}
	
	public Pedido Build()
	{
		TheLogger.getConsoleLogger().debug("Se crea el pedido:{}", this.pedido);
		return this.pedido;
		
	}
}
