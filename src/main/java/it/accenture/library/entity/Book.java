package it.accenture.library.entity;

import it.accenture.library.to.BookTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entità JPA che rappresenta un libro nella biblioteca.
 *
 * <p>Mappata sulla tabella {@code BOOK} del database H2.
 * L'id viene generato automaticamente dalla strategia {@code IDENTITY}
 * (auto-increment del database).</p>
 *
 * <p>Lombok genera getter, setter e il costruttore senza argomenti
 * richiesto dalla specifica JPA ({@code @NoArgsConstructor}).</p>
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "BOOK")
public class Book {

    /** Identificativo univoco del libro, generato automaticamente dal database. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Titolo del libro. */
    private String title;

    /** Nome e cognome dell'autore del libro. */
    private String author;

    /** Codice ISBN (International Standard Book Number). */
    private String isbn;

    /**
     * Costruttore di conversione da Transfer Object a entità.
     * Usato in {@code BookService.addBook()} per creare un nuovo record nel database.
     *
     * @param book il DTO in ingresso dalla richiesta REST; non deve essere {@code null}
     */
    public Book(BookTO book) {
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.isbn = book.getIsbn();
    }
}
