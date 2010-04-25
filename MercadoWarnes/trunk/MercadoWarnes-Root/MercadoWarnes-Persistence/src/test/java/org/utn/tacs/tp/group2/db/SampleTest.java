package org.utn.tacs.tp.group2.db;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.junit.Test;
import org.utn.tacs.tp.group2.hibernate.utils.HibernateUtil;
import org.utn.tacs.tp.group2.hibernate.utils.Command;
import org.utn.tacs.tp.group2.pedido.Pedido;

public class SampleTest extends TestCase {

	/**
	 * primer test, crea la base y le pone algunos registros
	 */
	@Test
	public void testInitialize() {
		HibernateUtil.doExecute(new Command() {

			public void execute(Session session) throws Exception {
				
				
				Pedido pedido = new Pedido();

				session.save(pedido);
				
			}
		});
	}
}