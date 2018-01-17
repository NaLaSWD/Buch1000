package at.fhj.swd.main;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "buch")
public class Buch {
    @Id
    private int id;
    private int isbn;
    private String titel;
    private int jahr;

    public Buch(int id, int isbn, int jahr, String titel) {
        setId(id);
        setIsbn(isbn);
        setJahr(jahr);
        setTitel(titel);
    }

    public Buch() {
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "isbn")
    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "titel")
    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    @Basic
    @Column(name = "jahr")
    public int getJahr() {
        return jahr;
    }

    public void setJahr(int jahr) {
        this.jahr = jahr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Buch that = (Buch) o;
        return id == that.id &&
                Objects.equals(isbn, that.isbn) &&
                Objects.equals(titel, that.titel) &&
                Objects.equals(jahr, that.jahr);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, isbn, titel, jahr);
    }
}