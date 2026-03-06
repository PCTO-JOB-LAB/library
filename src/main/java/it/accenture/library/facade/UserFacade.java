package it.accenture.library.facade;

import it.accenture.library.rto.UserRTO;
import it.accenture.library.service.UserService;
import it.accenture.library.to.UserTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Facade del dominio utente che espone operazioni semplificate al layer controller.
 *
 * <p>Fa da intermediario tra {@code UserController} e {@code UserService}, isolando
 * il controller dalla logica di business. La dependency injection avviene tramite
 * costruttore generato da Lombok ({@code @RequiredArgsConstructor}).</p>
 */
@Component
@RequiredArgsConstructor
public class UserFacade {

    /** Servizio di business per le operazioni sugli utenti. */
    private final UserService userService;

    /**
     * Recupera un utente per identificativo.
     *
     * @param id l'identificativo univoco dell'utente
     * @return il {@code UserRTO} corrispondente, oppure {@code null} se non trovato
     */
    public UserRTO findUserById(Long id) {
        return userService.findUserById(id);
    }

    /**
     * Recupera la lista di tutti gli utenti presenti nel database.
     *
     * @return lista (eventualmente vuota) di {@code UserRTO}
     */
    public List<UserRTO> findAllUsers() {
        return userService.findAll();
    }

    /**
     * Crea un nuovo utente nel database a partire dai dati del Transfer Object.
     *
     * @param userTO i dati dell'utente da creare
     * @return l'identificativo dell'utente appena creato
     */
    public Long addUser(UserTO userTO) {
        return userService.addUser(userTO);
    }
}
