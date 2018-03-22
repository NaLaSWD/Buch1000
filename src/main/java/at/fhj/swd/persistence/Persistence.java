package at.fhj.swd.persistence;

import javax.persistence.EntityManager;

public class Persistence extends at.fhj.swd.util.Persistence {

    public static final String persistenceUnit = "buchverzeichnis";

    public static EntityManager connect() {
        return connect(persistenceUnit);
    }

    public static EntityManager connect(String user, String password) {
        return connect(persistenceUnit, user, password);
    }
}
