package at.fhj.swd.buchverzeichnis.main;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Einzelbuero {
    @Id
    private int id;
    private String ort;
    private String strasse;
    private int plz;
    @OneToOne
    private Autor autor;

    public Einzelbuero() {
    }

    public Einzelbuero(int id, String ort, String strasse, int plz) {
        this.id = id;
        this.ort = ort;
        this.strasse = strasse;
        this.plz = plz;
        setAutor(autor);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public int getPlz() {
        return plz;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

}
