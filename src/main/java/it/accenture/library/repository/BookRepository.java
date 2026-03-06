package it.accenture.library.repository;

import it.accenture.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository JPA per la gestione delle entità {@code Book}.
 *
 * <p>Estende {@link JpaRepository} fornendo automaticamente le operazioni CRUD standard
 * (save, findById, findAll, delete, ecc.) per la tabella {@code BOOK} del database.
 * La chiave primaria è di tipo {@link Long}.</p>
 *
 * <p>Non sono definite query custom: tutte le operazioni necessarie sono coperte
 * dai metodi ereditati da {@code JpaRepository}.</p>
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
