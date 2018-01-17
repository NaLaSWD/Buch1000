package at.fhj.swd.main;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "autor")
public class Autor {
    @Id
    private int id;
    private String vorname;
    private String nachname;
    private String geb_datum;

    public Autor(int id, String vorname, String nachname, String geb_datum) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geb_datum = geb_datum;
    }

    public Autor() {
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "vorname")
    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    @Basic
    @Column(name = "nachname")
    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    @Basic
    @Column(name = "geb_datum")
    public String getGebDat() {
        return geb_datum;
    }

    public void setGebDat(String geb_datum) {
        this.geb_datum = geb_datum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return id == autor.id &&
                Objects.equals(vorname, autor.vorname) &&
                Objects.equals(nachname, autor.nachname) &&
                Objects.equals(geb_datum, autor.geb_datum);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, vorname, nachname, geb_datum);
    }

}
