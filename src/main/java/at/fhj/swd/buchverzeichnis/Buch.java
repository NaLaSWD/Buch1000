package at.fhj.swd.buchverzeichnis;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


@Entity

@NamedQueries({
    @NamedQuery(name  = "Buch.findBuchTitelByErscheinungsjahr",
                query = "SELECT b.titel " +
                        "FROM Buch b " +
                        "WHERE b.erscheinungsjahr = :Erscheinungsjahr"),

    @NamedQuery(name  = "Buch.findBuchIdByGenre",
                query = "SELECT b " +
                        "FROM Buch b INNER JOIN Genre g " +
                        "ON b.genre.id = g.id " +
                        "WHERE g.bezeichnung = :GenreName "),
    })

public class Buch {
    @Id
    private int id;
    private int isbn;
    private String titel;
    @Temporal(TemporalType.DATE)
    private Date erscheinungsjahr;
    @ManyToMany(mappedBy = "buecher")
    private Collection<Autor> autoren;
    @ManyToOne (cascade = CascadeType.PERSIST)
    private Genre genre;

    protected Buch() {
    }

    public Buch(int id, int isbn, Date erscheinungsjahr, String titel, Genre genre) {
        setId(id);
        setIsbn(isbn);
        setTitel(titel);
        setErscheinungsjahr(erscheinungsjahr);
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

    void addAutor(Autor autor) {
        if (!autoren.contains(autor)) {
            autoren.add(autor);
        }
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
        genre.addBuch(this);
    }
}