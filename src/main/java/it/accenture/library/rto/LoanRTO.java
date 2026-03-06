package it.accenture.library.rto;

import it.accenture.library.entity.Loan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Response Transfer Object (RTO) che rappresenta i dati di un prestito restituiti nelle risposte REST.
 *
 * <p>Aggrega informazioni provenienti da tre entità diverse (Loan, User, Book):
 * <ul>
 *   <li>{@code name} e {@code surname} — dati anagrafici dell'utente che ha effettuato il prestito</li>
 *   <li>{@code title}, {@code author}, {@code isbn} — dati del libro prestato</li>
 *   <li>{@code startDate}, {@code endDate} — date di inizio e fine del prestito (timestamp Unix)</li>
 * </ul>
 * I campi relativi a utente e libro ({@code name}, {@code surname}, {@code title}, {@code author},
 * {@code isbn}) vengono popolati direttamente dalle query JPQL di {@code LoanRepository}
 * tramite il costruttore {@link #LoanRTO(String, String, String, String, String, Long, Long)}
 * generato da {@code @AllArgsConstructor}.</p>
 *
 * <p>Il costruttore {@link #LoanRTO(Loan)} popola solo le date e viene mantenuto per compatibilità,
 * ma nella pratica i dati completi vengono sempre forniti tramite la query JPQL.</p>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanRTO {

    /** Nome dell'utente che ha effettuato il prestito. */
    private String name;

    /** Cognome dell'utente che ha effettuato il prestito. */
    private String surname;

    /** Titolo del libro oggetto del prestito. */
    private String title;

    /** Nome e cognome dell'autore del libro prestato. */
    private String author;

    /** Codice ISBN del libro prestato. */
    private String isbn;

    /** Data di inizio del prestito, espressa come timestamp Unix in millisecondi. */
    private Long startDate;

    /** Data di fine (restituzione) del prestito, espressa come timestamp Unix in millisecondi. */
    private Long endDate;

    /**
     * Costruttore di conversione parziale da entità {@code Loan} a RTO.
     *
     * <p>Popola solo le date di inizio e fine del prestito. I campi relativi a utente e libro
     * rimangono {@code null} perché vengono valorizzati dalla query JPQL tramite il costruttore
     * con tutti gli argomenti.</p>
     *
     * @param loan l'entità JPA sorgente; non deve essere {@code null}
     */
    public LoanRTO(Loan loan) {
        this.startDate = loan.getStartDate();
        this.endDate = loan.getEndDate();
    }

}
