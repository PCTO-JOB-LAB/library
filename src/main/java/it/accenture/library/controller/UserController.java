package it.accenture.library.controller;

import it.accenture.library.facade.UserFacade;
import it.accenture.library.rto.UserRTO;
import it.accenture.library.to.UserTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller REST per la gestione delle risorse utente ({@code /user}).
 *
 * <p>Espone tre endpoint:
 * <ul>
 *   <li>{@code GET /user/all} — lista di tutti gli utenti</li>
 *   <li>{@code GET /user/} — dettaglio di un utente per id</li>
 *   <li>{@code POST /user/} — creazione di un nuovo utente</li>
 * </ul>
 * La dependency injection avviene tramite costruttore generato da Lombok
 * ({@code @RequiredArgsConstructor}).</p>
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    /** Facade che orchestra la logica di business per gli utenti. */
    private final UserFacade userFacade;

    /**
     * Restituisce la lista di tutti gli utenti presenti nel database.
     *
     * @return {@code 200 OK} con la lista di {@code UserRTO}
     */
    @GetMapping("/all")
    public ResponseEntity<Object> findAllUsers() {
        return new ResponseEntity<>(userFacade.findAllUsers(), HttpStatus.OK);
    }

    /**
     * Restituisce il dettaglio di un utente identificato dal parametro {@code userId}.
     *
     * @param userId l'identificativo univoco dell'utente da cercare
     * @return {@code 200 OK} con il {@code UserRTO}, oppure {@code 404 Not Found}
     *         se l'utente non esiste
     */
    @GetMapping("/")
    public ResponseEntity<Object> findUserById(@RequestParam Long userId) {
        UserRTO result = userFacade.findUserById(userId);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Crea un nuovo utente nel database a partire dal corpo della richiesta JSON.
     *
     * @param userTO i dati dell'utente da creare, deserializzati dal corpo della richiesta
     * @return {@code 201 Created} con l'identificativo dell'utente appena creato
     */
    @PostMapping("/")
    public ResponseEntity<Object> addUser(@RequestBody UserTO userTO) {
        return new ResponseEntity<>(userFacade.addUser(userTO), HttpStatus.CREATED);
    }

}
