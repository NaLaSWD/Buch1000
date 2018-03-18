package at.fhj.swd.buchverzeichnis.main;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Verlag {
    @Id
    private int id;
    private String name;
    private String ort;
    private String strasse;
    private int plz;
    @OneToMany (mappedBy = "autor")
    private Collection<Autor> autoren = new ArrayList<Autor>();

    public Verlag(){
    }

    public Verlag(int id, String name, String ort, String strasse, int plz) {
        this.id = id;
        this.name = name;
        this.ort = ort;
        this.strasse = strasse;
        this.plz = plz;
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

    public void addAutor(Autor autor){
        if(!autoren.contains(autor)){
            autoren.add(autor);
        }
    }

    public Collection<Autor> getAutor() {
        return autoren;
    }


}
