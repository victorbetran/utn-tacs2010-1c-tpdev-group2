MercadoWarnes SH nos ha contratado para la realización de un sistema de subastas y ventas de autopartes nuevas y usadas. Para ello deberemos modelar el negocio y luego generar la aplicación que realice dichas transacciones. Nótese que la aplicación deberá soportar múltiples usuarios concurrentes y comunicarse con sistemas externos.

La idea de la aplicación es permitir una gestión centralizada de todas las operaciones de compra-venta realizadas por nuestro cliente. Además debe permitir integrar la aplicación con sus módulos actuales de facturación y su WebApp de consulta de precios on-line.

Para poder lograr estos objetivos la aplicación a desarrollar debe realizar las siguientes actividades de integración:
Exponer la lista de productos disponibles como servicios REST
Realizar la generación de un pedido en el sistema de facturación a través de SOAP
Procesar un pedido de registración en el sistema de facturación y de inmovilización de productos a través de una cola JMS