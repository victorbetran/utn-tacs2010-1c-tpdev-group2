package org.utn.tacs.tp.group2.dtos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "PIEZA")
public class PiezaDTO {

	//********************************************
	//** ATRIBUTTES
	//********************************************
	@Id
	@GeneratedValue(generator="sequence")
	@org.hibernate.annotations.GenericGenerator
	(name="sequence", 
	 strategy = "sequence", 
	 parameters = {@Parameter(name="sequence", value="PIEZAIDSEQUENCE")})
    @Column(name = "PIEZA_ID")
	private Long id = null;
	
	@ManyToOne
    @JoinColumn(name = "PEDIDO_ID")
    @org.hibernate.annotations.ForeignKey(name="FK_PEDIDO_ID")
    private PedidoDTO pedido;
	
	@Column(name = "CODIGO")
	private String codigo;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;

	
	//********************************************
	//** GETTERS AND SETTERS
	//********************************************
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return this.codigo;
	}
	
	public void setPedido(PedidoDTO pedidoDTO) {
		this.pedido = pedidoDTO;
	}
	
	public PedidoDTO getPedido() {
		return this.pedido;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}
}
