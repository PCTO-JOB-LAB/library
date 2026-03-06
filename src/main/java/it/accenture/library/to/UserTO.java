package it.accenture.library.to;

import it.accenture.library.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Transfer Object (TO) utilizzato per trasportare i dati di un utente tra i layer applicativi.
 *
 * <p>Nelle richieste POST in ingresso viene deserializzato dal corpo JSON e passato
 * al layer di servizio per la creazione di un nuovo utente. Contiene solo i campi
 * anagrafici dell'utente, senza l'identificativo (assegnato dal database).</p>
 *
 * <p>Il costruttore {@link #UserTO(User)} permette la conversione inversa da entità a TO
 * (ad esempio per popolare form di modifica), ma non è attualmente usato nel flusso
 * principale dell'applicazione.</p>
 */
@Getter
@Setter
@NoArgsConstructor
public class UserTO {

    /** Nome dell'utente. */
    private String name;

    /** Cognome dell'utente. */
    private String surname;

    /**
     * Costruttore di conversione da entità {@code User} a Transfer Object.
     *
     * <p>Mappa i campi anagrafici dell'entità JPA nel TO. L'identificativo non viene copiato
     * perché il TO rappresenta solo i dati modificabili/inseribili dall'utente finale.</p>
     *
     * @param user l'entità JPA sorgente; non deve essere {@code null}
     */
    public UserTO(User user) {
        this.name = user.getName();
        this.surname = user.getSurname();
    }

}
