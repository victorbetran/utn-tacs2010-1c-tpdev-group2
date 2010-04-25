package org.utn.tacs.tp.group2.hibernate.utils;

import org.hibernate.Session;

public interface Command {

    void execute(Session session) throws Exception;
}
