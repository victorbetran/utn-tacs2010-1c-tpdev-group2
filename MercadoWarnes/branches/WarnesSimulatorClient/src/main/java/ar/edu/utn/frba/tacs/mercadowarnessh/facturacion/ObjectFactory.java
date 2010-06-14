
package ar.edu.utn.frba.tacs.mercadowarnessh.facturacion;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.edu.utn.frba.tacs.mercadowarnessh.facturacion package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Pieza_QNAME = new QName("http://facturacion.mercadowarnessh.tacs.frba.utn.edu.ar/", "pieza");
    private final static QName _Pedido_QNAME = new QName("http://facturacion.mercadowarnessh.tacs.frba.utn.edu.ar/", "pedido");
    private final static QName _NotificarPedidoEfectivoResponse_QNAME = new QName("http://facturacion.mercadowarnessh.tacs.frba.utn.edu.ar/", "notificarPedidoEfectivoResponse");
    private final static QName _NotificarPedidoEfectivo_QNAME = new QName("http://facturacion.mercadowarnessh.tacs.frba.utn.edu.ar/", "notificarPedidoEfectivo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.edu.utn.frba.tacs.mercadowarnessh.facturacion
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NotificarPedidoEfectivo }
     * 
     */
    public NotificarPedidoEfectivo createNotificarPedidoEfectivo() {
        return new NotificarPedidoEfectivo();
    }

    /**
     * Create an instance of {@link Pedido }
     * 
     */
    public Pedido createPedido() {
        return new Pedido();
    }

    /**
     * Create an instance of {@link Pieza }
     * 
     */
    public Pieza createPieza() {
        return new Pieza();
    }

    /**
     * Create an instance of {@link NotificarPedidoEfectivoResponse }
     * 
     */
    public NotificarPedidoEfectivoResponse createNotificarPedidoEfectivoResponse() {
        return new NotificarPedidoEfectivoResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Pieza }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.mercadowarnessh.tacs.frba.utn.edu.ar/", name = "pieza")
    public JAXBElement<Pieza> createPieza(Pieza value) {
        return new JAXBElement<Pieza>(_Pieza_QNAME, Pieza.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Pedido }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.mercadowarnessh.tacs.frba.utn.edu.ar/", name = "pedido")
    public JAXBElement<Pedido> createPedido(Pedido value) {
        return new JAXBElement<Pedido>(_Pedido_QNAME, Pedido.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotificarPedidoEfectivoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.mercadowarnessh.tacs.frba.utn.edu.ar/", name = "notificarPedidoEfectivoResponse")
    public JAXBElement<NotificarPedidoEfectivoResponse> createNotificarPedidoEfectivoResponse(NotificarPedidoEfectivoResponse value) {
        return new JAXBElement<NotificarPedidoEfectivoResponse>(_NotificarPedidoEfectivoResponse_QNAME, NotificarPedidoEfectivoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotificarPedidoEfectivo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://facturacion.mercadowarnessh.tacs.frba.utn.edu.ar/", name = "notificarPedidoEfectivo")
    public JAXBElement<NotificarPedidoEfectivo> createNotificarPedidoEfectivo(NotificarPedidoEfectivo value) {
        return new JAXBElement<NotificarPedidoEfectivo>(_NotificarPedidoEfectivo_QNAME, NotificarPedidoEfectivo.class, null, value);
    }

}
