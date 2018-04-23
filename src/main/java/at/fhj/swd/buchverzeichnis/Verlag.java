package at.fhj.swd.buchverzeichnis;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
//neuer query Verlag
@NamedQueries({
        @NamedQuery( name = "Verlag.findVerlagOrtByAutorNachname",
                    query = "SELECT a.nachname, v.ort " +
                            "FROM Autor a LEFT JOIN Verlag v " +
                            "ON a.verlag.id = v.id " +
                            "WHERE v.ort = :Ort"),
})

public class Verlag {
    @Id
    private int id;
    private String name;
    private String ort;
    private String strasse;
    private int plz;
    @OneToMany(mappedBy = "verlag")
    private Collection<Autor> autoren;

    protected Verlag(){
    }

    public Verlag(int id, String name, String ort, String strasse, int plz) {
        setId(id);
        setName(name);
        setOrt(ort);
        setStrasse(strasse);
        setPlz(plz);
        autoren = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


    public Collection<Autor> getAutor() {
        return autoren;
    }

    void addAutor(Autor autor) {
        if (!autoren.contains(autor)) {
            autoren.add(autor);
        }
    }
}
