package it.accenture.library.exception;

import lombok.Getter;

/**
 * Eccezione unchecked custom per il dominio della biblioteca.
 *
 * <p>Estende {@link RuntimeException} per permettere la propagazione senza obbligo di
 * dichiarazione nei metodi chiamanti. Viene lanciata dal layer facade quando non sono
 * soddisfatti i requisiti di business (es. nessun filtro di ricerca valorizzato).</p>
 *
 * <p>Il campo {@code customMessage} è dichiarato {@code final} perché il messaggio
 * non deve essere modificato dopo la costruzione dell'eccezione.</p>
 */
@Getter
public class BibliotecaException extends RuntimeException {

    /** Numero di versione per la serializzazione della classe. */
    private static final long serialVersionUID = -4679239882266446242L;

    /**
     * Messaggio di errore personalizzato del dominio, esposto tramite getter Lombok.
     * Dichiarato {@code final} perché immutabile dopo la costruzione.
     */
    private final String customMessage;

    /**
     * Costruisce l'eccezione con un messaggio personalizzato.
     *
     * <p>Il messaggio viene passato sia al costruttore di {@link RuntimeException}
     * (accessibile tramite {@link #getMessage()}) sia memorizzato nel campo
     * {@code customMessage} per esposizione diretta nei controller.</p>
     *
     * @param message il messaggio di errore da propagare; non deve essere {@code null}
     */
    public BibliotecaException(final String message) {
        super(message);
        this.customMessage = message;
    }

    /**
     * Costruisce l'eccezione senza messaggio personalizzato.
     *
     * <p>Il campo {@code customMessage} viene impostato a {@code null}.
     * Usato nei casi in cui non sia disponibile un messaggio specifico.</p>
     */
    public BibliotecaException() {
        super();
        this.customMessage = null;
    }

}
