package at.fhj.swd.main;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "einzelbuero")
public class Einzelbuero {
    @Id
    private int id;
    private String ort;
    private String strasse;
    private int plz;
    ^
    @OneToOne
    private Autor autor;

    public Einzelbuero(int id, String ort, String strasse, int plz) {
        setId(id);
        setOrt(ort);
        setStrasse(strasse);
        setPlz(plz);
    }

    public Einzelbuero() {
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
    @Column(name = "ort")
    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    @Basic
    @Column(name = "strasse")
    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    @Basic
    @Column(name = "plz")
    public int getPlz() {
        return plz;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Einzelbuero that = (Einzelbuero) o;
        return id == that.id &&
                Objects.equals(ort, that.ort) &&
                Objects.equals(strasse, that.strasse) &&
                Objects.equals(plz, that.plz);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, ort, strasse, plz);
    }
}
