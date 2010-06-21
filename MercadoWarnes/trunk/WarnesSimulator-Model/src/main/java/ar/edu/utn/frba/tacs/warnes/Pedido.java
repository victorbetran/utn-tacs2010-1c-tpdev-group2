package ar.edu.utn.frba.tacs.warnes;

import java.io.Serializable;
import java.util.List;

public class Pedido implements Serializable{
	private static final long serialVersionUID = -7679065821742046152L;
	private Long numero;
	private Cliente cliente;
	private List<Item> items;

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public Double calcularTotal(){
		Double total = 0D;
		for(Item i: this.items){
			total += i.calcularTotal();
		}
		
		return total;
	}
}
