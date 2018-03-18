package at.fhj.swd.buchverzeichnis.main;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Genre {
    @Id
    private int id;
    private String genre;
    @OneToMany (mappedBy = "buch")
    private Collection<Buch> buecher = new ArrayList<Buch>();

    protected Genre(){
    }

    public Genre(int id, String genre) {
        this.id = id;
        this.genre = genre;
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

    public void addBuch(Buch buch){
        if(!buecher.contains(buch)){
            buecher.add(buch);
        }
    }

    public Collection<Buch> getBuch() {
        return buecher;
    }

}
