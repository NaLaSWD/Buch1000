package at.fhj.swd.main;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "genre")
public class Genre {
    @Id
    private int id;
    private String genre;

    @OneToMany (mappedBy = "genre" )
    private Collection<Buch> buch = new ArrayList<Buch>();

    public Genre(int id, String genre, List<Buch> buch) {
        this.id = id;
        this.genre = genre;
        buch = new ArrayList<>();
    }

    public Genre() {
    }

    @Basic
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "genre")
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    //OneToMany
    public Collection<Buch> getBuch() {
        return buch;
    }

    public void setBuch(Collection<Buch> buch) {
        this.buch = buch;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre1 = (Genre) o;
        return id == genre1.id &&
                Objects.equals(genre, genre1.genre);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, genre);
    }
}
