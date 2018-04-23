package at.fhj.swd.buchverzeichnis;

import javax.persistence.*;


@Entity
@NamedQueries({
    @NamedQuery(name = "Einzelbuero.findEinzelbueroOrtByAutorID",
                query = "SELECT a.id, e.ort " +
                        "FROM Autor a LEFT JOIN Einzelbuero e " +
                        "ON a.einzelbuero.id = e.id " +
                        "WHERE e.ort = :Ort"),

})

public class Einzelbuero {
    @Id
    private int id;
    private String ort;
    private String strasse;
    private int plz;
    @OneToOne(mappedBy = "einzelbuero")
    private Autor autor;

    protected Einzelbuero() {
    }

    public Einzelbuero(int id, String ort, String strasse, int plz) {
        setId(id);
        setOrt(ort);
        setStrasse(strasse);
        setPlz(plz);
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

    void setAutor(Autor autor) {
        if(this.autor == null){
            this.autor = autor;
        }
    }
}
