package it.accenture.library.facade;

import it.accenture.library.rto.BookRTO;
//import it.accenture.library.service.BookDescriptionService;
import it.accenture.library.service.BookService;
import it.accenture.library.to.BookTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Facade del dominio libro che espone operazioni semplificate al layer controller.
 *
 * <p>Fa da intermediario tra {@code BookController} e {@code BookService}, isolando
 * il controller dalla logica di business. La dependency injection avviene tramite
 * costruttore generato da Lombok ({@code @RequiredArgsConstructor}).</p>
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class BookFacade {

    /** Servizio di business per le operazioni sui libri. */
    private final BookService bookService;
//    private final BookDescriptionService bookDescriptionService;

    /**
     * Recupera un libro per identificativo, arricchito dalla descrizione AI.
     *
     * @param id l'identificativo univoco del libro
     * @return il {@code BookRTO} corrispondente, oppure {@code null} se non trovato
     */
    public BookRTO findBookById(Long id) {
        BookRTO bookRTO = bookService.findBookById(id);
//        try {
//            log.info("Chiamata AI per arricchimento descrizione...");
//            String description = bookDescriptionService.describeBook(bookRTO.getTitle(),
//                                                                     bookRTO.getAuthor());
//            bookRTO.setDescription(description);
//            log.info("Descrizione AI ricevuta con successo");
//        } catch (Exception e) {
//            log.warn("Chiamata AI fallita ({}): {}", e.getClass().getSimpleName(), e.getMessage());
//            bookRTO.setDescription("[Descrizione AI non disponibile]");
//        }

        return bookRTO;
    }

    /**
     * Delete by id.
     *
     * @param id the id
     */
    public void deleteById(Long id) {
        bookService.deleteById(id);
    }

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

    public List<BookRTO> getByFilter(Long bookId,
                                     String title,
                                     String author,
                                     String isbn) {

        return bookService.getByFilter(bookId,
                                       title,
                                       author,
                                       isbn);
    }
}
