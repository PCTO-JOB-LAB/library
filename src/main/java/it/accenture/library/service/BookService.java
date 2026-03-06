package it.accenture.library.service;

import it.accenture.library.entity.Book;
import it.accenture.library.repository.BookRepository;
import it.accenture.library.rto.BookRTO;
import it.accenture.library.to.BookTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servizio di business per la gestione dei libri nella biblioteca.
 *
 * <p>Coordina le operazioni CRUD sul repository JPA {@code BookRepository} e arricchisce
 * i dati del singolo libro con una descrizione generata dal servizio AI
 * {@code BookDescriptionService}.</p>
 *
 * <p>La dependency injection avviene tramite costruttore (constructor injection), generato
 * da Lombok con {@code @RequiredArgsConstructor}; tutti i collaboratori sono dichiarati
 * {@code private final} per garantire l'immutabilità del wiring.</p>
 */
@Service
@RequiredArgsConstructor
public class BookService {

    private static final Logger log = LoggerFactory.getLogger(BookService.class);

    /** Repository JPA per l'accesso ai dati della tabella {@code BOOK}. */
    private final BookRepository bookRepository;

    /** Servizio AI per la generazione di descrizioni dei libri. */
    private final BookDescriptionService bookDescriptionService;

    /**
     * Ricerca un libro per identificativo e ne arricchisce la risposta con una descrizione AI.
     *
     * <p>Se il libro non è presente nel database viene restituito {@code null} e viene
     * registrato un warning nel log. In caso di errore nella chiamata AI, la descrizione
     * viene impostata al testo di fallback {@code "[Descrizione AI non disponibile]"}.</p>
     *
     * @param id l'identificativo univoco del libro da cercare
     * @return il {@code BookRTO} popolato con i dati e la descrizione AI,
     *         oppure {@code null} se il libro non esiste
     */
    public BookRTO findBookById(Long id) {
        log.info("findBookById chiamato con id={}", id);
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            log.warn("Libro con id={} non trovato nel database", id);
            return null;
        }
        log.info("Libro trovato: {} - {}", book.get().getTitle(), book.get().getAuthor());
        BookRTO bookRTO = new BookRTO(book.get());
        try {
            log.info("Chiamata AI per arricchimento descrizione...");
            bookRTO.setDescription(bookDescriptionService.describeBook(book.get().getTitle(), book.get().getAuthor()));
            log.info("Descrizione AI ricevuta con successo");
        } catch (Exception e) {
            log.warn("Chiamata AI fallita ({}): {}", e.getClass().getSimpleName(), e.getMessage());
            bookRTO.setDescription("[Descrizione AI non disponibile]");
        }
        return bookRTO;
    }

    /**
     * Restituisce la lista di tutti i libri presenti nel database.
     *
     * <p>Ogni entità {@code Book} viene convertita in {@code BookRTO} tramite il
     * costruttore di conversione. La descrizione AI non viene richiesta per le liste.</p>
     *
     * @return lista (eventualmente vuota) di {@code BookRTO}
     */
    public List<BookRTO> findAll() {
        return bookRepository.findAll().stream()
                .map(BookRTO::new)
                .collect(Collectors.toList());
    }

    /**
     * Persiste un nuovo libro nel database a partire dai dati del Transfer Object.
     *
     * @param bookTO i dati del libro da creare, provenienti dalla richiesta REST
     * @return l'identificativo generato dal database per il nuovo libro
     */
    public Long addBook(BookTO bookTO) {
        Book book = new Book(bookTO);
        bookRepository.save(book);
        return book.getId();
    }
}
