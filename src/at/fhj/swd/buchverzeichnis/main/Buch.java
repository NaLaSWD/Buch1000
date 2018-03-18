package at.fhj.swd.buchverzeichnis.main;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


@Entity
public class Buch {
    @Id
    private int id;
    private int isbn;
    private String titel;
    private Date erscheinungsjahr;
    @ManyToMany (mappedBy = "buch")
    private Collection<Autor> autoren = new ArrayList<>();
    @ManyToOne
    private Genre genre;

    public Buch() {
    }

    public Buch(int id, int isbn, Date erscheinungsjahr, String titel) {
        this.id = id;
        this.isbn = isbn;
        this.titel = titel;
        this.erscheinungsjahr = erscheinungsjahr;
        autoren = new ArrayList<>();
        setGenre(genre);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public Date getErscheinungsjahr() {
        return erscheinungsjahr;
    }

    public void setErscheinungsjahr(Date jahr) {
        this.erscheinungsjahr = jahr;
    }

    public Collection<Autor> getAutor() {
        return autoren;
    }

    public void addAutor(Autor autor){
        if(!autoren.contains(autor)){
            autoren.add(autor);
        }
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

}