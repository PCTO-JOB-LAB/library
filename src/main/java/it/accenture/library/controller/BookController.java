package it.accenture.library.controller;

import it.accenture.library.facade.BookFacade;
import it.accenture.library.rto.BookRTO;
import it.accenture.library.to.BookTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller REST per la gestione delle risorse libro ({@code /books}).
 *
 * <p>Espone tre endpoint:
 * <ul>
 *   <li>{@code GET /books/all} — lista di tutti i libri</li>
 *   <li>{@code GET /books/} — dettaglio di un libro per id (con descrizione AI)</li>
 *   <li>{@code POST /books/} — creazione di un nuovo libro</li>
 * </ul>
 * La dependency injection avviene tramite costruttore generato da Lombok
 * ({@code @RequiredArgsConstructor}).</p>
 */
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    /** Facade che orchestra la logica di business per i libri. */
    private final BookFacade bookFacade;

    /**
     * Restituisce la lista di tutti i libri presenti nel database.
     *
     * @return {@code 200 OK} con la lista di {@code BookRTO}
     */
    @GetMapping("/all")
    public ResponseEntity<Object> findAllBooks() {
        return new ResponseEntity<>(bookFacade.findAllBooks(), HttpStatus.OK);
    }

    /**
     * Restituisce il dettaglio di un libro identificato dal parametro {@code bookId},
     * arricchito con la descrizione generata dal servizio AI.
     *
     * @param bookId l'identificativo univoco del libro da cercare
     * @return {@code 200 OK} con il {@code BookRTO}, oppure {@code 404 Not Found}
     *         se il libro non esiste
     */
    @GetMapping("/")
    public ResponseEntity<Object> findBookById(@RequestParam Long bookId) {
        BookRTO result = bookFacade.findBookById(bookId);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Crea un nuovo libro nel database a partire dal corpo della richiesta JSON.
     *
     * @param bookTO i dati del libro da creare, deserializzati dal corpo della richiesta
     * @return {@code 201 Created} con l'identificativo del libro appena creato
     */
    @PostMapping("/")
    public ResponseEntity<Object> addBook(@RequestBody BookTO bookTO) {
        return new ResponseEntity<>(bookFacade.addBook(bookTO), HttpStatus.CREATED);
    }

}
