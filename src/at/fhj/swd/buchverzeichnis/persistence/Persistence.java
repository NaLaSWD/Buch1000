package at.fhj.swd.buchverzeichnis.persistence;

import javax.persistence.EntityManager;

public class Persistence extends at.fhj.swd.buchverzeichnis.util.Persistence
{

    public static final String persistenceUnit = "db_lanner16";


    public static EntityManager connect () {
        return connect (persistenceUnit);
    }

    public static EntityManager connect (String user, String password) {
        return connect (persistenceUnit, user, password);
    }
}
