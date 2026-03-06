package it.accenture.library.to;

import lombok.Getter;
import lombok.Setter;

/**
 * Transfer Object (TO) utilizzato per ricevere i dati di un libro nelle richieste REST in ingresso.
 *
 * <p>Viene deserializzato dal corpo della richiesta HTTP (JSON) nei metodi POST del
 * {@code BookController} e passato al layer di servizio per la creazione di una nuova entità
 * {@code Book} nel database.</p>
 */
@Getter
@Setter
public class BookTO {

    /** Titolo del libro. */
    private String title;

    /** Nome e cognome dell'autore del libro. */
    private String author;

    /** Codice ISBN (International Standard Book Number) del libro. */
    private String isbn;

}
