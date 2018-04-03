package at.fhj.swd.buchverzeichnis;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@NamedQueries({
    @NamedQuery(name  = "Genre.findGenreIDByBuchTitel",
                query = "SELECT G.ID, G.Genre " +
                        "FROM Genre G INNER JOIN Buch B " +
                        "ON G.ID = B.Genre_ID " +
                        "WHERE B.Titel = :Buchtitel"),

    @NamedQuery(name  = "Genre.findBuchTitelByBuchErscheinungsjahrAndGenre",
                query = "SELECT G.ID, G.Genre, B.Titel "+
                        "FROM Genre G INNER JOIN Buch B " +
                        "ON G.ID = B.Genre_ID "+
                        "WHERE G.Genre = :Genre AND B.Erscheinungsjahr = :Erscheinungsjahr")

})

public class Genre {
    @Id
    private int id;
    private String genre_bezeichnung;
    @OneToMany (mappedBy = "genre")
    private Collection<Buch> buecher;

    protected Genre(){
    }

    public Genre(int id, String genre) {
        setId(id);
        setGenre_bezeichnung(genre);
        buecher = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenre_bezeichnung() {
        return genre_bezeichnung;
    }

    public void setGenre_bezeichnung(String genre) {
        this.genre_bezeichnung = genre;
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
