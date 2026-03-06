package it.accenture.library.controller;

import it.accenture.library.exception.BibliotecaException;
import it.accenture.library.facade.LoanFacade;
import it.accenture.library.to.LoanTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller REST per la gestione delle risorse prestito ({@code /loan}).
 *
 * <p>Espone tre endpoint:
 * <ul>
 *   <li>{@code GET /loan/all} — lista di tutti i prestiti</li>
 *   <li>{@code GET /loan/} — ricerca prestiti per filtri opzionali (userId e/o bookId)</li>
 *   <li>{@code POST /loan/} — creazione di un nuovo prestito</li>
 * </ul>
 * La dependency injection avviene tramite costruttore generato da Lombok
 * ({@code @RequiredArgsConstructor}).</p>
 */
@RestController
@RequestMapping("/loan")
@RequiredArgsConstructor
public class LoanController {

    /** Facade che orchestra la logica di business per i prestiti. */
    private final LoanFacade loanFacade;

    /**
     * Restituisce la lista di tutti i prestiti, arricchita con i dati di utente e libro.
     *
     * @return {@code 200 OK} con la lista di {@code LoanRTO}
     */
    @GetMapping("/all")
    public ResponseEntity<Object> findAllLoans() {
        return new ResponseEntity<>(loanFacade.findAllLoans(), HttpStatus.OK);
    }

    /**
     * Ricerca i prestiti applicando i filtri opzionali per utente e/o libro.
     *
     * <p>Almeno uno dei due parametri deve essere valorizzato: se nessuno è presente
     * viene restituito {@code 400 Bad Request} con il messaggio di errore.</p>
     *
     * @param userId l'identificativo dell'utente da filtrare; opzionale
     * @param bookId l'identificativo del libro da filtrare; opzionale
     * @return {@code 200 OK} con la lista dei prestiti filtrati,
     *         oppure {@code 400 Bad Request} se nessun filtro è valorizzato
     */
    @GetMapping("/")
    public ResponseEntity<Object> findLoanByFilters(@RequestParam(required = false) Long userId,
                                                    @RequestParam(required = false) Long bookId) {
        try {
            return new ResponseEntity<>(loanFacade.findLoan(userId, bookId), HttpStatus.OK);
        } catch (BibliotecaException ex) {
            return new ResponseEntity<>(ex.getCustomMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Crea un nuovo prestito nel database a partire dal corpo della richiesta JSON.
     *
     * @param loanTO i dati del prestito da creare, deserializzati dal corpo della richiesta
     * @return {@code 201 Created} con l'identificativo utente del prestito appena creato
     */
    @PostMapping("/")
    public ResponseEntity<Object> addLoan(@RequestBody LoanTO loanTO) {
        return new ResponseEntity<>(loanFacade.addLoan(loanTO), HttpStatus.CREATED);
    }

}
