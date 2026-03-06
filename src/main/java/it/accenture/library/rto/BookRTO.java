package it.accenture.library.rto;

import it.accenture.library.entity.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Response Transfer Object (RTO) che rappresenta i dati di un libro restituiti nelle risposte REST.
 *
 * <p>Contiene i campi base del libro più il campo {@code description}, che viene popolato
 * dalla chiamata al servizio AI ({@code BookDescriptionService}) all'interno di
 * {@code BookService.findBookById()}.</p>
 *
 * <p>Il costruttore senza argomenti è generato da Lombok tramite {@code @NoArgsConstructor}
 * e viene usato internamente da {@code BookService.findAll()} tramite lo stream.</p>
 */
@Getter
@Setter
@NoArgsConstructor
public class BookRTO {

    /** Titolo del libro. */
    private String title;

    /** Nome e cognome dell'autore del libro. */
    private String author;

    /** Codice ISBN (International Standard Book Number) del libro. */
    private String isbn;

    /** Descrizione sintetica generata dal servizio AI; può essere {@code null} in caso di errore. */
    private String description;

    /**
     * Costruttore di conversione da entità {@code Book} a RTO.
     *
     * <p>Copia i campi anagrafici del libro. Il campo {@code description} non è valorizzato
     * qui ma viene impostato separatamente tramite {@code setDescription()} dopo la chiamata AI.</p>
     *
     * @param book l'entità JPA sorgente; non deve essere {@code null}
     */
    public BookRTO(Book book) {
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.isbn = book.getIsbn();
    }

}
