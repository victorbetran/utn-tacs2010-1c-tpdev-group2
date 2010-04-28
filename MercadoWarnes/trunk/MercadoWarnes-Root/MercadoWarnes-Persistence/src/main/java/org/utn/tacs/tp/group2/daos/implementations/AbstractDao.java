package org.utn.tacs.tp.group2.daos.implementations;

import org.hibernate.Session;
import org.utn.tacs.tp.group2.hibernate.SessionProvider;

public class AbstractDao {

    public static void doExecute(final Command closure) {
        Session session = null;
        session = SessionProvider.getInstance().getSession();
        closure.execute(session);
   }
	
}
