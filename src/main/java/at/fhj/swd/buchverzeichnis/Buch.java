package at.fhj.swd.buchverzeichnis;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


@Entity

@NamedQueries({
    @NamedQuery(name  = "Buch.findBuchIDTitelbyAutorFirstName",
                query = "SELECT b.id, b.titel " +
                        "FROM Buch b INNER JOIN Autor_Buch ab " +
                        "ON ab.buecher.id = b.id INNER JOIN Autor a " +
                        "ON ab.autoren.id = a.id " +
                        "WHERE a.vorname  = :AutorFirstname"),

    @NamedQuery(name  = "Buch.findBuchIDByErscheinungsjahr",
                query = "SELECT b.id, b.titel " +
                        "FROM Buch b " +
                        "WHERE b.erscheinungsjahr = :Erscheinungsjahr"),

    @NamedQuery(name  = "Buch.findBuchIDByGenre",
                query = "SELECT b.ID, B.Titel " +
                        "FROM BUCH B INNER JOIN Genre G " +
                        "ON B.Genre_ID = G.ID " +
                        "WHERE G.Bezeichnung = :GenreName"),
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

    public void addAutor(Autor autor) {
        if (!autoren.contains(autor)) {
            autoren.add(autor);
        }
        autor.addBuch(this);
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

}