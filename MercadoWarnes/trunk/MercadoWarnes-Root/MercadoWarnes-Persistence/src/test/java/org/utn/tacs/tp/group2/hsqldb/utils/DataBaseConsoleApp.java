package org.utn.tacs.tp.group2.hsqldb.utils;

/**
 * Levanta la consola para poder ver el esquema de ls DB y realizar queries.
 */
public class DataBaseConsoleApp {

    public static void main(String[] args) {
        org.hsqldb.util.DatabaseManager.main(args);
    }
}