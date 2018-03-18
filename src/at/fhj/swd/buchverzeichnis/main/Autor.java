package at.fhj.swd.buchverzeichnis.main;

import javax.persistence.*;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
public class Autor {
    @Id
    private int id;
    private String vorname;
    private String nachname;
    private Date geb_datum;
    @ManyToMany (mappedBy = "buch")
    private Collection<Buch> buecher;
    @ManyToOne
    private Verlag verlag;
    @OneToOne
    private Einzelbuero einzelbuero;

    public Autor(){
    }

    public Autor(int id, String vorname, String nachname, Date geb_datum) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geb_datum = geb_datum;
        buecher = new ArrayList<>();
        setVerlag(verlag);
        setEinzelbuero(einzelbuero);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public Date getGeb_datum() {
        return geb_datum;
    }

    public void setGeb_datum(Date geb_datum) {
        this.geb_datum = geb_datum;
    }


    public void addBuch(Buch buch) {
        if (!buecher.contains(buch)) {
            buecher.add(buch);
        }
    }

    public Collection<Buch> getBuch() {
        return buecher;
    }

    public Verlag getVerlag() {
        return verlag;
    }

    public void setVerlag(Verlag verlag) {
        this.verlag = verlag;
    }

    public Einzelbuero getEinzelbuero() {
        return einzelbuero;
    }

    public void setEinzelbuero(Einzelbuero einzelbuero) {
        this.einzelbuero = einzelbuero;
    }
}
