package it.accenture.library.service;

import it.accenture.library.entity.Book;
import it.accenture.library.repository.BookRepository;
import it.accenture.library.rto.BookRTO;
import it.accenture.library.to.BookTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {


    /**
     * Repository JPA per l'accesso ai dati della tabella {@code BOOK}.
     */
    private final BookRepository bookRepository;


    /**
     * Restituisce la lista di tutti i libri presenti nel database.
     *
     * <p>Ogni entità {@code Book} viene convertita in {@code BookRTO} tramite il
     * costruttore di conversione. La descrizione AI non viene richiesta per le liste.</p>
     *
     * @return lista (eventualmente vuota) di {@code BookRTO}
     */
    public List<BookRTO> findAll() {
        return bookRepository.findAll()
                             .stream()
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
