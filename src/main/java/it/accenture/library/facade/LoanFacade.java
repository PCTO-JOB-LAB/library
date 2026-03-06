package it.accenture.library.facade;

import it.accenture.library.exception.BibliotecaException;
import it.accenture.library.rto.LoanRTO;
import it.accenture.library.service.LoanService;
import it.accenture.library.to.LoanTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Facade del dominio prestito che espone operazioni semplificate al layer controller.
 *
 * <p>Fa da intermediario tra {@code LoanController} e {@code LoanService}, isolando
 * il controller dalla logica di business. Gestisce la logica di filtraggio dei prestiti
 * in base ai parametri opzionali {@code userId} e {@code bookId}.
 * La dependency injection avviene tramite costruttore generato da Lombok
 * ({@code @RequiredArgsConstructor}).</p>
 */
@Component
@RequiredArgsConstructor
public class LoanFacade {

    /** Servizio di business per le operazioni sui prestiti. */
    private final LoanService loanService;

    /**
     * Ricerca i prestiti applicando i filtri opzionali per utente e/o libro.
     *
     * <p>La logica di selezione segue questa precedenza:
     * <ol>
     *   <li>Se entrambi i parametri sono valorizzati, filtra per utente e libro.</li>
     *   <li>Se solo {@code userId} è valorizzato, filtra per utente.</li>
     *   <li>Se solo {@code bookId} è valorizzato, filtra per libro.</li>
     *   <li>Se nessun parametro è valorizzato, lancia {@code BibliotecaException}.</li>
     * </ol>
     * </p>
     *
     * @param userId l'identificativo dell'utente da filtrare; può essere {@code null}
     * @param bookId l'identificativo del libro da filtrare; può essere {@code null}
     * @return lista dei prestiti che soddisfano i criteri di filtro
     * @throws BibliotecaException se nessun parametro di filtro è valorizzato
     */
    public List<LoanRTO> findLoan(Long userId, Long bookId) {
        if (userId != null && bookId != null) {
            return loanService.findLoanByUserIdAndBookId(userId, bookId);
        } else if (userId != null) {
            return loanService.findByUserId(userId);
        } else if (bookId != null) {
            return loanService.findByBookId(bookId);
        }

        throw new BibliotecaException("Valorizzare un filtro di ricerca");
    }

    /**
     * Restituisce la lista di tutti i prestiti, arricchita con i dati di utente e libro.
     *
     * @return lista (eventualmente vuota) di {@code LoanRTO}
     */
    public List<LoanRTO> findAllLoans() {
        return loanService.findAll();
    }

    /**
     * Crea un nuovo prestito nel database a partire dai dati del Transfer Object.
     *
     * @param loanTO i dati del prestito da creare
     * @return l'identificativo utente dalla chiave primaria composita del prestito appena creato
     */
    public Long addLoan(LoanTO loanTO) {
        return loanService.addLoan(loanTO);
    }
}
