package ar.edu.utn.frba.tacs.mercadowarnessh.facturacion;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.2.1
 * Sat Jun 19 18:43:58 GMT-03:00 2010
 * Generated source version: 2.2.1
 * 
 */
 
@WebService(targetNamespace = "http://facturacion.mercadowarnessh.tacs.frba.utn.edu.ar/", name = "PedidosService")
@XmlSeeAlso({ObjectFactory.class})
public interface PedidosService {

    @RequestWrapper(localName = "notificarPedidoEfectivo", targetNamespace = "http://facturacion.mercadowarnessh.tacs.frba.utn.edu.ar/", className = "ar.edu.utn.frba.tacs.mercadowarnessh.facturacion.NotificarPedidoEfectivo")
    @ResponseWrapper(localName = "notificarPedidoEfectivoResponse", targetNamespace = "http://facturacion.mercadowarnessh.tacs.frba.utn.edu.ar/", className = "ar.edu.utn.frba.tacs.mercadowarnessh.facturacion.NotificarPedidoEfectivoResponse")
    @WebMethod
    public void notificarPedidoEfectivo(
        @WebParam(name = "arg0", targetNamespace = "")
        ar.edu.utn.frba.tacs.mercadowarnessh.facturacion.Pedido arg0
    );
}