package org.utn.tacs.tp.group2.persistence.utils;

/**
 * Esta clase sirve para levantar la DB como server.
 */
public class DataBaseServerStartUp {

	public static void main(String[] args) {
        org.hsqldb.Server.main(args);
   }
	
}
