package at.fhj.swd.buchverzeichnis;

import javax.persistence.*;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Date;


@Entity
@NamedQueries({
    @NamedQuery(name  = "Autor.findAutorByVerlag",
                query = "SELECT a " +
                        "FROM Autor a " +
                        "WHERE a.verlag.name  = :Verlag"),

    @NamedQuery(name  = "Autor.findEinzelbueroByVerlag",
                query = "SELECT a.einzelbuero.strasse " +
                        "FROM Autor a " +
                        "WHERE a.verlag.name = :Verlag"),

})

public class Autor {
    @Id
    private int id;
    private String vorname;
    private String nachname;
    @Temporal(TemporalType.DATE)
    private Date geb_datum;
    @ManyToMany
    private Collection<Buch> buecher;
    @ManyToOne
    private Verlag verlag;
    @OneToOne
    private Einzelbuero einzelbuero;

    protected Autor(){
    }

    public Autor(int id, String vorname, String nachname, Date geb_datum, Verlag verlag, Einzelbuero einzelbuero) {
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

    //Buch
    void addBuch(Buch buch) {
        if (!buecher.contains(buch)) {
            buecher.add(buch);
        }
    }

    public Collection<Buch> getBuch() {
        return buecher;
    }

    //Verlag
    public Verlag getVerlag() {
        return verlag;
    }

    public void setVerlag(Verlag verlag) {
        this.verlag = verlag;
        verlag.addAutor(this);
    }

    //Einzelbuero
    public Einzelbuero getEinzelbuero() {
        return einzelbuero;
    }

    public void setEinzelbuero(Einzelbuero einzelbuero){
        this.einzelbuero = einzelbuero;
        einzelbuero.setAutor(this);
    }
}
