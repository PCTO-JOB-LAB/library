package it.accenture.library.rto;

import it.accenture.library.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Response Transfer Object (RTO) che rappresenta i dati di un utente restituiti nelle risposte REST.
 *
 * <p>Contiene i campi anagrafici dell'utente (nome e cognome) senza esporre l'identificativo
 * interno del database. Viene costruito a partire dall'entità {@code User} tramite il
 * costruttore di conversione {@link #UserRTO(User)}.</p>
 *
 * <p>Il costruttore senza argomenti è generato da Lombok tramite {@code @NoArgsConstructor}.</p>
 */
@Getter
@Setter
@NoArgsConstructor
public class UserRTO {

    /** Nome dell'utente. */
    private String name;

    /** Cognome dell'utente. */
    private String surname;

    /**
     * Costruttore di conversione da entità {@code User} a RTO.
     *
     * <p>Copia i campi anagrafici dell'utente. Viene usato come method reference
     * ({@code UserRTO::new}) nello stream di {@code UserService.findAll()}.</p>
     *
     * @param user l'entità JPA sorgente; non deve essere {@code null}
     */
    public UserRTO(User user) {
        this.name = user.getName();
        this.surname = user.getSurname();
    }

}
