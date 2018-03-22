package at.fhj.swd.buchverzeichnis;

import javax.persistence.*;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Date;


@Entity
public class Autor {
    @Id
    private int id;
    private String vorname;
    private String nachname;
    @Temporal(TemporalType.DATE)
    private Date geb_datum;
    @ManyToMany //(mappedBy = "autor")
    private Collection<Buch> buecher;
    @ManyToOne
    private Verlag verlag;
    @OneToOne //(mappedBy = "autor")
    private Einzelbuero einzelbuero;

    protected Autor(){
    }

    public Autor(int id, String vorname, String nachname, Date geb_datum) {
        setId(id);
        setVorname(vorname);
        setNachname(nachname);
        setGeb_datum(geb_datum);
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


    void addBuch(Buch buch) {
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
        verlag.addAutor(this);
    }

    public Einzelbuero getEinzelbuero() {
        return einzelbuero;
    }

    void setEinzelbuero(Einzelbuero einzelbuero){
        this.einzelbuero = einzelbuero;
        einzelbuero.setAutor(this);
    }
}
