package org.utn.tacs.tp.group2.sample;

import org.hibernate.Session;

public interface SessionClosure {

    void execute(Session session) throws Exception;
}
