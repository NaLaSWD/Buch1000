package at.fhj.swd.buchverzeichnis;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@NamedQueries({
        @NamedQuery(name  = "Genre.findGenreBezeichnungByBuchTitel",
                query = "SELECT g.bezeichnung " +
                        "FROM Genre g INNER JOIN Buch b " +
                        "ON g.id = b.genre.id " +
                        "WHERE b.titel = :Buchtitel"),

        @NamedQuery(name  = "Genre.findBuchTitelByGenre",
                query = "SELECT g.buecher "+
                        "FROM Genre g " +
                        //"INNER JOIN Buch b " +
                        //"ON g.id = b.genre.id "+
                        "WHERE g.bezeichnung = :Genre")
})

public class Genre {
    @Id
    private int id;
    private String bezeichnung;
    @OneToMany (mappedBy = "genre")
    private Collection<Buch> buecher;

    protected Genre(){
    }

    public Genre(int id, String bezeichnung) {
        setId(id);
        setBezeichnung(bezeichnung);
        buecher = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String genre) {
        this.bezeichnung = genre;
    }

    void addBuch(Buch buch){
        if(!buecher.contains(buch)){
            buecher.add(buch);
        }
    }

    public Collection<Buch> getBuecher() {
        return buecher;
    }

}