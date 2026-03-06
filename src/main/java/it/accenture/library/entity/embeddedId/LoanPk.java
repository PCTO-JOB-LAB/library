package it.accenture.library.entity.embeddedId;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Chiave primaria composita dell'entità {@code Loan} (prestito).
 *
 * <p>Implementa {@link Serializable} come richiesto dalla specifica JPA per le chiavi
 * primarie embedded. I due campi ({@code userId} e {@code bookId}) identificano
 * univocamente la coppia utente-libro coinvolta in un prestito.</p>
 *
 * <p>Lombok genera getter, setter, costruttore senza argomenti (richiesto da JPA) e
 * costruttore con tutti gli argomenti per creare facilmente istanze valorizzate.</p>
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanPk implements Serializable {

    /** Identificativo dell'utente che ha effettuato il prestito. */
    @Column(name = "user_id")
    private Long userId;

    /** Identificativo del libro oggetto del prestito. */
    @Column(name = "book_id")
    private Long bookId;

}
