# Library — Gestione Prestiti Biblioteca

API REST per la gestione di una biblioteca: libri, utenti e prestiti.
Il progetto include un'integrazione AI con **Anthropic Claude** tramite **LangChain4j**: quando si recupera un singolo libro, l'API arricchisce la risposta con una descrizione generata dall'AI.

---

## Tech Stack

| Tecnologia | Versione |
|---|---|
| Java | 25 |
| Spring Boot | 3.4.5 |
| Spring Data JPA | (gestito da Spring Boot) |
| H2 Database | (in-memory / TCP) |
| LangChain4j Anthropic | 0.36.0 |
| SpringDoc OpenAPI | 2.6.0 |
| Lombok | (gestito da Spring Boot) |

---

## Prerequisiti

- **Java 25** installato (`java -version`)
- **Maven** installato (`mvn -version`)
- **API Key Anthropic** — ottienila su [console.anthropic.com](https://console.anthropic.com)
- **H2 Server** in esecuzione in modalità TCP (oppure modifica il datasource per usare H2 in-memory)

---

## Configurazione

Apri `src/main/resources/application.properties` e sostituisci il placeholder con la tua API key:

```properties
langchain4j.anthropic.chat-model.api-key=sk-ant-TUACHIAVE
```

---

## Avvio

```bash
mvn spring-boot:run
```

L'applicazione si avvia su `http://localhost:8080`.

---

## Endpoint REST

### Libri

| Metodo | URL | Descrizione |
|---|---|---|
| `GET` | `/books/all` | Tutti i libri |
| `GET` | `/books/?bookId={id}` | Libro per ID (con descrizione AI) |
| `POST` | `/books/` | Aggiunge un libro |

**Esempio request body (POST /books/):**
```json
{
  "title": "Il Signore degli Anelli",
  "author": "J.R.R. Tolkien",
  "isbn": "978-8845292613"
}
```

**Esempio response (GET /books/?bookId=1):**
```json
{
  "title": "Il Signore degli Anelli",
  "author": "J.R.R. Tolkien",
  "isbn": "978-8845292613",
  "description": "Il Signore degli Anelli è un'epica saga fantasy ambientata nella Terra di Mezzo..."
}
```

### Utenti

| Metodo | URL | Descrizione |
|---|---|---|
| `GET` | `/user/all` | Tutti gli utenti |
| `GET` | `/user/?userId={id}` | Utente per ID |
| `POST` | `/user/` | Aggiunge un utente |

### Prestiti

| Metodo | URL | Descrizione |
|---|---|---|
| `GET` | `/loan/all` | Tutti i prestiti (con dati utente e libro) |
| `GET` | `/loan/?userId={id}` | Prestiti di un utente |
| `GET` | `/loan/?bookId={id}` | Prestiti di un libro |
| `POST` | `/loan/` | Aggiunge un prestito |

---

## Funzionalità AI

Quando si chiama `GET /books/?bookId={id}`, il sistema:

1. Recupera il libro dal database H2
2. Invia titolo e autore ad **Anthropic Claude** tramite LangChain4j
3. Riceve una descrizione di 2-3 frasi in italiano
4. Restituisce la descrizione nel campo `description` della risposta JSON

> La chiamata AI avviene solo sul singolo libro (`GET /books/?bookId=`), non sulla lista (`GET /books/all`), per evitare chiamate multiple all'API.

---

## Documentazione API

Swagger UI disponibile all'avvio su:

```
http://localhost:8080/swagger-ui/index.html
```

Console H2 disponibile su:

```
http://localhost:8080/h2-console
```
