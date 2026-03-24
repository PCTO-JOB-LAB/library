package it.accenture.library.entity;

import it.accenture.library.to.UserTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entità JPA che rappresenta un utente (lettore) della biblioteca.
 *
 * <p>Mappata sulla tabella {@code people} del database H2.
 * L'id viene generato automaticamente dalla strategia {@code IDENTITY}.</p>
 *
 * <p>Lombok genera getter, setter e il costruttore senza argomenti
 * richiesto dalla specifica JPA ({@code @NoArgsConstructor}).</p>
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PEOPLE")
public class User {

    /** Identificativo univoco dell'utente, generato automaticamente dal database. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nome dell'utente. */
    private String name;

    /** Cognome dell'utente. */
    private String surname;

    /**
     * Costruttore di conversione da Transfer Object a entità.
     * Usato in {@code UserService.addUser()} per creare un nuovo utente nel database.
     *
     * @param userTO il DTO in ingresso dalla richiesta REST; non deve essere {@code null}
     */
    public User(UserTO userTO) {
        this.name = userTO.getName();
        this.surname = userTO.getSurname();
    }
}
