package ar.edu.utn.frba.tacs.mercadowarnessh.facturacion;

import javax.jws.WebService;

@WebService
public interface PedidosService {

	public void notificarPedidoEfectivo(Pedido pedido);

}