package it.accenture.library.service;

import it.accenture.library.entity.Loan;
import it.accenture.library.repository.LoanRepository;
import it.accenture.library.rto.LoanRTO;
import it.accenture.library.to.LoanTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servizio di business per la gestione dei prestiti della biblioteca.
 *
 * <p>Coordina le operazioni CRUD e di ricerca filtrata sul repository JPA {@code LoanRepository}.
 * La dependency injection avviene tramite costruttore generato da Lombok
 * ({@code @RequiredArgsConstructor}); il repository è dichiarato {@code private final}
 * per garantire l'immutabilità del wiring.</p>
 */
@Service
@RequiredArgsConstructor
public class LoanService {

    /** Repository JPA per l'accesso ai dati della tabella dei prestiti. */
    private final LoanRepository loanRepository;

    /**
     * Restituisce la lista di tutti i prestiti, arricchita con i dati di utente e libro.
     *
     * @return lista (eventualmente vuota) di {@code LoanRTO}
     */
    public List<LoanRTO> findAll() {
        return loanRepository.findAllLoans();
    }

    /**
     * Ricerca i prestiti filtrati per identificativo utente e identificativo libro.
     *
     * @param userId l'identificativo dell'utente da filtrare
     * @param bookId l'identificativo del libro da filtrare
     * @return lista dei prestiti che soddisfano entrambi i criteri, ordinata per data decrescente
     */
    public List<LoanRTO> findLoanByUserIdAndBookId(Long userId, Long bookId) {
        return loanRepository.findLoanByUserIdAndBookId(userId, bookId);
    }

    /**
     * Ricerca i prestiti effettuati da un utente specifico.
     *
     * @param userId l'identificativo dell'utente di cui recuperare i prestiti
     * @return lista dei prestiti dell'utente, ordinata per data decrescente
     */
    public List<LoanRTO> findByUserId(Long userId) {
        return loanRepository.findLoanByUserId(userId);
    }

    /**
     * Ricerca i prestiti relativi a un libro specifico.
     *
     * @param bookId l'identificativo del libro di cui recuperare i prestiti
     * @return lista dei prestiti del libro, ordinata per data decrescente
     */
    public List<LoanRTO> findByBookId(Long bookId) {
        return loanRepository.findLoanByBookId(bookId);
    }

    /**
     * Persiste un nuovo prestito nel database a partire dai dati del Transfer Object.
     *
     * @param loanTO i dati del prestito da creare, provenienti dalla richiesta REST
     * @return l'identificativo utente dalla chiave primaria composita del prestito appena creato
     */
    public Long addLoan(LoanTO loanTO) {
        Loan loan = new Loan(loanTO);
        loanRepository.save(loan);
        return loan.getId().getUserId();
    }

}
