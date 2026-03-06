package it.accenture.library.to;

import it.accenture.library.entity.Loan;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Transfer Object (TO) utilizzato per ricevere i dati di un prestito nelle richieste REST in ingresso.
 *
 * <p>Nelle richieste POST viene deserializzato dal corpo JSON e passato al layer di servizio
 * per la creazione di un nuovo prestito. Contiene i riferimenti agli identificativi di utente
 * e libro coinvolti, più le date di inizio e fine prestito espresse come timestamp Unix (millisecondi).</p>
 *
 * <p>Il costruttore {@link #LoanTO(Loan)} permette la conversione inversa da entità a TO.</p>
 */
@Getter
@Setter
@NoArgsConstructor
public class LoanTO {

    /** Identificativo dell'utente che effettua il prestito. */
    private Long userId;

    /** Identificativo del libro oggetto del prestito. */
    private Long bookId;

    /** Data di inizio del prestito, espressa come timestamp Unix in millisecondi. */
    private Long startDate;

    /** Data di fine (restituzione) del prestito, espressa come timestamp Unix in millisecondi. */
    private Long endDate;

    /**
     * Costruttore di conversione da entità {@code Loan} a Transfer Object.
     *
     * <p>Estrae gli identificativi dalla chiave primaria composita {@code LoanPk} e
     * copia le date di inizio e fine prestito.</p>
     *
     * @param loan l'entità JPA sorgente; non deve essere {@code null}
     */
    public LoanTO(Loan loan) {
        this.userId = loan.getId().getUserId();
        this.bookId = loan.getId().getBookId();
        this.startDate = loan.getStartDate();
        this.endDate = loan.getEndDate();
    }

}
