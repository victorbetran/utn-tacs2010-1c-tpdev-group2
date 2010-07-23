package ar.edu.utn.frba.tacs.mercadowarnessh.facturacion;

import javax.jws.WebService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@WebService(endpointInterface = "ar.edu.utn.frba.tacs.mercadowarnessh.facturacion.PedidosService")
public class PedidosServiceImpl implements PedidosService {

	private final Log log = LogFactory.getLog(PedidosServiceImpl.class);

	public void notificarPedidoEfectivo(Pedido pedido) {
		log.info("Pedido efectivo notificado - " + pedido);
	}
}
