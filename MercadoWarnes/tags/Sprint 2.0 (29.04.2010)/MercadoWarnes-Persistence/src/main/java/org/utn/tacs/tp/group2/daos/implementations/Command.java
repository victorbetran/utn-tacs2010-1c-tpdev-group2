package org.utn.tacs.tp.group2.daos.implementations;

import org.hibernate.Session;

public interface Command {

	void execute(Session session);

}
