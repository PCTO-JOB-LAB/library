package it.accenture.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Classe di avvio dell'applicazione Spring Boot per la gestione della biblioteca.
 *
 * <p>Configura il component scan sull'intero package {@code it.accenture} per includere
 * tutti i bean Spring (controller, facade, service, repository, ecc.) definiti nei
 * sotto-package del progetto.</p>
 *
 * <p>L'annotazione {@code @SpringBootApplication} combina {@code @Configuration},
 * {@code @EnableAutoConfiguration} e {@code @ComponentScan} con configurazione
 * predefinita sul package corrente; il {@code @ComponentScan} esplicito estende
 * la scansione al package radice {@code it.accenture}.</p>
 */
@SpringBootApplication
@ComponentScan({ "it.accenture" })
public class LibraryApplication {

	/**
	 * Punto di ingresso dell'applicazione.
	 *
	 * <p>Avvia il contesto Spring Boot, inizializza tutti i bean e avvia il server
	 * embedded (Tomcat di default).</p>
	 *
	 * @param args argomenti a riga di comando passati all'avvio; possono contenere
	 *             proprietà Spring nel formato {@code --chiave=valore}
	 */
	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

}
