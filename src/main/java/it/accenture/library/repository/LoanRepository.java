package it.accenture.library.repository;

import it.accenture.library.entity.Loan;
import it.accenture.library.entity.embeddedId.LoanPk;
import it.accenture.library.rto.LoanRTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository JPA per la gestione delle entità {@code Loan} (prestiti).
 *
 * <p>Estende {@link JpaRepository} con chiave primaria composita {@link LoanPk}.
 * Definisce query JPQL personalizzate che eseguono join tra le entità {@code Loan},
 * {@code User} e {@code Book} per restituire direttamente oggetti {@link LoanRTO}
 * tramite il costruttore con tutti gli argomenti.</p>
 */
@Repository
public interface LoanRepository extends JpaRepository<Loan, LoanPk> {

    /**
     * Restituisce tutti i prestiti presenti nel database, arricchiti con i dati
     * anagrafici dell'utente e del libro tramite join JPQL.
     *
     * @return lista (eventualmente vuota) di {@code LoanRTO}
     */
    @Query("""
            SELECT new it.accenture.library.rto.LoanRTO(
                U.name, U.surname, B.title, B.author, B.isbn, L.startDate, L.endDate)
            FROM Loan L
            JOIN User U ON L.id.userId = U.id
            JOIN Book B ON L.id.bookId = B.id
            """)
    List<LoanRTO> findAllLoans();

    /**
     * Ricerca i prestiti effettuati da un utente specifico, ordinati per data di inizio decrescente.
     *
     * @param userId l'identificativo dell'utente da filtrare; non deve essere {@code null}
     * @return lista dei prestiti dell'utente, ordinata per {@code startDate} decrescente
     */
    @Query("""
            SELECT new it.accenture.library.rto.LoanRTO(
                U.name, U.surname, B.title, B.author, B.isbn, L.startDate, L.endDate)
            FROM Loan L
            JOIN User U ON L.id.userId = U.id
            JOIN Book B ON L.id.bookId = B.id
            WHERE U.id = :userId
            ORDER BY L.startDate DESC
            """)
    List<LoanRTO> findLoanByUserId(@Param("userId") Long userId);

    /**
     * Ricerca i prestiti relativi a un libro specifico, ordinati per data di inizio decrescente.
     *
     * @param bookId l'identificativo del libro da filtrare; non deve essere {@code null}
     * @return lista dei prestiti del libro, ordinata per {@code startDate} decrescente
     */
    @Query("""
            SELECT new it.accenture.library.rto.LoanRTO(
                U.name, U.surname, B.title, B.author, B.isbn, L.startDate, L.endDate)
            FROM Loan L
            JOIN User U ON L.id.userId = U.id
            JOIN Book B ON L.id.bookId = B.id
            WHERE B.id = :bookId
            ORDER BY L.startDate DESC
            """)
    List<LoanRTO> findLoanByBookId(@Param("bookId") Long bookId);

    /**
     * Ricerca i prestiti filtrati per identificativo utente e identificativo libro,
     * ordinati per data di inizio decrescente.
     *
     * @param userId l'identificativo dell'utente da filtrare; non deve essere {@code null}
     * @param bookId l'identificativo del libro da filtrare; non deve essere {@code null}
     * @return lista dei prestiti che soddisfano entrambi i criteri, ordinata per {@code startDate} decrescente
     */
    @Query("""
            SELECT new it.accenture.library.rto.LoanRTO(
                U.name, U.surname, B.title, B.author, B.isbn, L.startDate, L.endDate)
            FROM Loan L
            JOIN User U ON L.id.userId = U.id
            JOIN Book B ON L.id.bookId = B.id
            WHERE U.id = :userId
              AND B.id = :bookId
            ORDER BY L.startDate DESC
            """)
    List<LoanRTO> findLoanByUserIdAndBookId(@Param("userId") Long userId, @Param("bookId") Long bookId);
}
