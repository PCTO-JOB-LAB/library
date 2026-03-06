-- Libri
INSERT INTO BOOK (id ,title, author, isbn) VALUES (1, 'Il Nome della Rosa', 'Umberto Eco', '978-88-452-2887-3');
INSERT INTO BOOK (id ,title, author, isbn) VALUES (2, 'I Promessi Sposi', 'Alessandro Manzoni', '978-88-04-65954-7');
INSERT INTO BOOK (id ,title, author, isbn) VALUES (3, 'Il Signore degli Anelli', 'J.R.R. Tolkien', '978-88-452-9219-4');
INSERT INTO BOOK (id ,title, author, isbn) VALUES (4, '1984', 'George Orwell', '978-88-04-51357-4');
INSERT INTO BOOK (id ,title, author, isbn) VALUES (5, 'Il Piccolo Principe', 'Antoine de Saint-Exupery', '978-88-17-11764-7');

-- Utenti
INSERT INTO PEOPLE (id ,name, surname) VALUES (1, 'Mario', 'Rossi');
INSERT INTO PEOPLE (id ,name, surname) VALUES (2, 'Laura', 'Bianchi');
INSERT INTO PEOPLE (id ,name, surname) VALUES (3, 'Giulio', 'Ferrari');

-- Prestiti (date in millisecondi)
-- Mario Rossi ha preso Il Nome della Rosa: 15-gen-2024 → 15-feb-2024
INSERT INTO LOAN (user_id, book_id, start_date, end_date) VALUES (1, 1, 1705276800000, 1707955200000);
-- Laura Bianchi ha preso Il Signore degli Anelli: 20-feb-2024 → 20-mar-2024
INSERT INTO LOAN (user_id, book_id, start_date, end_date) VALUES (2, 3, 1708387200000, 1710892800000);
-- Giulio Ferrari ha preso Il Piccolo Principe: 01-mar-2024 → 01-apr-2024
INSERT INTO LOAN (user_id, book_id, start_date, end_date) VALUES (3, 5, 1709251200000, 1711929600000);
