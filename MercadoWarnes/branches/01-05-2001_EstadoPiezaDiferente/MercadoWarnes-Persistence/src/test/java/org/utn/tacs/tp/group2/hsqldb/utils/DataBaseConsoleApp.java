package org.utn.tacs.tp.group2.hsqldb.utils;

/**
 * Esta clase levanta una aplicacion que nos permite realizar consultas sencillas
 * a la Base de Datos.
 * Cuando la ejecuten, donde dice URL deben poner: 
 * jdbc:hsqldb:file:db/tp-group2-db
 */
public class DataBaseConsoleApp {

    public static void main(String[] args) {
        org.hsqldb.util.DatabaseManager.main(args);
    }
}