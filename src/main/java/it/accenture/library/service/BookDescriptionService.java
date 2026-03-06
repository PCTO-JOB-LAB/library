package it.accenture.library.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

/**
 * Servizio AI per la generazione di descrizioni di libri tramite LangChain4j.
 *
 * <p>L'interfaccia è annotata con {@code @AiService}: LangChain4j crea automaticamente
 * un'implementazione che invoca il modello linguistico configurato nell'applicazione.</p>
 *
 * <p>Il prompt di sistema imposta il contesto del modello come assistente bibliotecario
 * e richiede risposte in italiano. Il prompt utente viene costruito dinamicamente
 * con il titolo e l'autore del libro da descrivere.</p>
 */
@AiService
public interface BookDescriptionService {

    /**
     * Genera una breve descrizione del libro specificato tramite chiamata al modello AI.
     *
     * <p>Il modello riceve un prompt di sistema che lo identifica come assistente bibliotecario
     * e un prompt utente che richiede una descrizione di 2-3 frasi.</p>
     *
     * @param title  il titolo del libro da descrivere; non deve essere {@code null}
     * @param author il nome dell'autore del libro; non deve essere {@code null}
     * @return la descrizione generata dal modello AI, in italiano
     */
    @SystemMessage("Sei un assistente bibliotecario. Rispondi sempre in italiano.")
    @UserMessage("Dammi una breve descrizione del libro '{{title}}' di {{author}} in 2-3 frasi.")
    String describeBook(@V("title") String title, @V("author") String author);

}
