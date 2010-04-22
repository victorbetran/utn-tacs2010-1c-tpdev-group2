package org.utn.tacs.tp.group2.sample;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.junit.Test;
import org.utn.tacs.tp.group2.pedido.Pedido;
import org.utn.tacs.tp.group2.pieza.Pieza;
import org.utn.tacs.tp.group2.utils.HibernateUtil;


public class SampleTest extends TestCase {

	/**
	 * primer test, crea la base y le pone algunos registros
	 */
	@Test
	public void testInitialize() {

		HibernateUtil.doExecute(new SessionClosure() {

			public void execute(Session session) throws Exception {

				Pedido p = new Pedido();
				p.addPieza(new Pieza("unCodigo"));
				session.save(new Pedido());

			}
		});
	}
}