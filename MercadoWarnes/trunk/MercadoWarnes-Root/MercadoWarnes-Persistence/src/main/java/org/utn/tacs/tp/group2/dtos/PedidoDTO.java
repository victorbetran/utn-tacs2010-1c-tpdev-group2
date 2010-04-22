package org.utn.tacs.tp.group2.dtos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PEDIDO")
public class PedidoDTO {
	
	//********************************************
	//** ATRIBUTTES
	//********************************************
	@Id 
	@GeneratedValue(generator="sequence")
	@org.hibernate.annotations.GenericGenerator(name="sequence", strategy = "sequence", 
	parameters = {@org.hibernate.annotations.Parameter(name="sequence", value="PEDIDOIDSEQUENCE")})
    @Column(name = "PEDIDO_ID")
    private Long id = null;
	
	@OneToMany(mappedBy ="pedido", fetch = FetchType.LAZY)
	@org.hibernate.annotations.Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "PEDIDO_ID")
	private List<PiezaDTO> piezas = new ArrayList<PiezaDTO>();

	
	//********************************************
	//** GETTERS AND SETTERS
	//********************************************
	public List<PiezaDTO> getPiezas() {
		return this.piezas;
	}

	public void setPiezas(List<PiezaDTO> piezas) {
		this.piezas = piezas;
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	//********************************************
	//** PUBLIC METHODS
	//********************************************
	public void addPieza(PiezaDTO pieza){
		this.getPiezas().add(pieza);
		pieza.setPedido(this);
	}
}
