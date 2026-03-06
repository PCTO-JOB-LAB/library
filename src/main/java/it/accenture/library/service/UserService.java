package it.accenture.library.service;

import it.accenture.library.entity.User;
import it.accenture.library.repository.UserRepository;
import it.accenture.library.rto.UserRTO;
import it.accenture.library.to.UserTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servizio di business per la gestione degli utenti della biblioteca.
 *
 * <p>Coordina le operazioni CRUD sul repository JPA {@code UserRepository}.
 * La dependency injection avviene tramite costruttore generato da Lombok
 * ({@code @RequiredArgsConstructor}); il repository è dichiarato {@code private final}
 * per garantire l'immutabilità del wiring.</p>
 */
@Service
@RequiredArgsConstructor
public class UserService {

    /** Repository JPA per l'accesso ai dati della tabella degli utenti. */
    private final UserRepository userRepository;

    /**
     * Ricerca un utente per identificativo e lo converte in RTO.
     *
     * @param id l'identificativo univoco dell'utente da cercare
     * @return il {@code UserRTO} corrispondente, oppure {@code null} se l'utente non esiste
     */
    public UserRTO findUserById(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        return userOpt.map(UserRTO::new).orElse(null);
    }

    /**
     * Restituisce la lista di tutti gli utenti presenti nel database.
     *
     * <p>Ogni entità {@code User} viene convertita in {@code UserRTO} tramite
     * il costruttore di conversione, usando uno stream.</p>
     *
     * @return lista (eventualmente vuota) di {@code UserRTO}
     */
    public List<UserRTO> findAll() {
        return userRepository.findAll().stream()
                .map(UserRTO::new)
                .collect(Collectors.toList());
    }

    /**
     * Persiste un nuovo utente nel database a partire dai dati del Transfer Object.
     *
     * @param userTO i dati dell'utente da creare, provenienti dalla richiesta REST
     * @return l'identificativo generato dal database per il nuovo utente
     */
    public Long addUser(UserTO userTO) {
        User user = new User(userTO);
        userRepository.save(user);
        return user.getId();
    }

}
