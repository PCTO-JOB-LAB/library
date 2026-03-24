package it.accenture.library.facade;

import it.accenture.library.rto.BookRTO;
import it.accenture.library.service.BookService;
import it.accenture.library.to.BookTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Facade del dominio libro che espone operazioni semplificate al layer controller.
 *
 * <p>Fa da intermediario tra {@code BookController} e {@code BookService}, isolando
 * il controller dalla logica di business. La dependency injection avviene tramite
 * costruttore generato da Lombok ({@code @RequiredArgsConstructor}).</p>
 */
@Component
@RequiredArgsConstructor
public class BookFacade {

    /** Servizio di business per le operazioni sui libri. */
    private final BookService bookService;

    /**
     * Recupera la lista di tutti i libri presenti nel database.
     *
     * @return lista (eventualmente vuota) di {@code BookRTO}
     */
    public List<BookRTO> findAllBooks() {
        return bookService.findAll();
    }

    /**
     * Crea un nuovo libro nel database a partire dai dati del Transfer Object.
     *
     * @param bookTO i dati del libro da creare
     * @return l'identificativo del libro appena creato
     */
    public Long addBook(BookTO bookTO) {
        return bookService.addBook(bookTO);
    }
}
