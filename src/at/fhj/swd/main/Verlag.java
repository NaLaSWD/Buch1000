package at.fhj.swd.main;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "verlag")
public class Verlag {
    @Id
    private int id;
    private String name;
    private String ort;
    private String strasse;
    private int plz;

    public Verlag(int id, String name, String ort, String strasse, int plz) {
        this.id = id;
        this.name = name;
        this.ort = ort;
        this.strasse = strasse;
        this.plz = plz;
    }

    public Verlag() {
    }

    @Basic
    @Column(name ="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name ="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name ="ort")
    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    @Basic
    @Column(name ="strasse")
    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    @Basic
    @Column(name ="plz")
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
        Verlag verlag = (Verlag) o;
        return id == verlag.id &&
                plz == verlag.plz &&
                Objects.equals(name, verlag.name) &&
                Objects.equals(ort, verlag.ort) &&
                Objects.equals(strasse, verlag.strasse);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, ort, strasse, plz);
    }
}
