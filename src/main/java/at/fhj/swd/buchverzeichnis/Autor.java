package at.fhj.swd.buchverzeichnis;

import javax.persistence.*;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Date;


@Entity
/*@NamedQueries({
    @NamedQuery(name  = "Autor.findAutorByVerlag",
                query = "SELECT A.ID AS AutorID, A.Nachname AS Autor_LastName, V.Name AS Verlag "+
                        "FROM Autor A LEFT JOIN Verlag V "+
                        "ON A.Verlag_ID = V.ID " +
                        "WHERE V.Name  = :Verlag"),

    @NamedQuery(name  = "Autor.findEinzelbueroByVerlag",
                query = "SELECT E.ID AS BueroID, E.Strasse, V.Name AS Verlagname "+
                        "FROM Verlag V INNER JOIN Autor A "+
                        "ON V.ID = A.Verlag_ID INNER JOIN Einzelbuero E " +
                        "ON E.ID = A.Einzelbuero_ID " +
                        "WHERE V.ID = :Verlag"),
})*/

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
