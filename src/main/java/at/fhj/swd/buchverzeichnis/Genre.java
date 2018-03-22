package at.fhj.swd.buchverzeichnis;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Genre {
    @Id
    private int id;
    private String genre;
    @OneToMany(mappedBy = "genre")
    private Collection<Buch> buecher;

    protected Genre(){
    }

    public Genre(int id, String genre) {
        setId(id);
        setGenre(genre);
        buecher = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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
