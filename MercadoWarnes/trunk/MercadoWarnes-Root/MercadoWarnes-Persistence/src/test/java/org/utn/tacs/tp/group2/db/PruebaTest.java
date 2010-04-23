package org.utn.tacs.tp.group2.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Test;
import org.utn.tacs.tp.group2.pedido.Pedido;
import org.utn.tacs.tp.group2.pieza.Pieza;

public class PruebaTest {

	@Test
	public void cargarDatos() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;

		try {
			// Levantamos session Factory
			sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
			// Abrimos session
			session = sessionFactory.openSession();
			// Abrimos transaccion manualmente->Puede hacerse automatico mediante AUTOCOMMIT
			transaction = session.beginTransaction();

			/**
			 * LABURO
			 */
			Pedido pedido = new Pedido();
			Pieza pieza = new Pieza("codigo");

			pieza.setDescripcion("soy una descripcion");

			pedido.addPieza(pieza);

			session.save(pedido);
			/**
			 * LABURO
			 */

			// COMMITEO LA TRANSACCION
			transaction.commit();
		}

		catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			if (session != null) {
				session.close();
				sessionFactory.close();
			}
		}
	}

}
