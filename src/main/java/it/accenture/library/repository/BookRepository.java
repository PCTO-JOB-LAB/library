package it.accenture.library.repository;

import it.accenture.library.entity.Book;
import it.accenture.library.rto.BookRTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * Find by title book.
     *
     * @param title the title
     * @return the book
     */
    @Query("select b from Book b where b.title = ?1")
    Book findByTitle(String title);

    /**
     * Gets by filter.
     *
     * @param bookId the book id
     * @param title  the title
     * @param author the author
     * @param isbn   the isbn
     * @return the by filter
     */
    @Query("""
           SELECT new it.accenture.library.rto.BookRTO(b)
           FROM Book b
           WHERE (:id IS NULL OR b.id = :id)
             AND (:title IS NULL OR b.title = :title)
             AND (:author IS NULL OR b.author = :author)
             AND (:isbn IS NULL OR b.isbn = :isbn)
           """)
    List<BookRTO> getByFilter(@Param("id") Long bookId,
                              @Param("title") String title,
                              @Param("author") String author,
                              @Param("isbn") String isbn);
}
