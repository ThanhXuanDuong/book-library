# book-library

Baue eine Anwendung, in der Du Deine Bücher verwalten kannst.

- GET /books liefert alle gespeicherten Bücher
- GET /books/0345391802 liefert das Buch mit der ISBN 0345391802
- PUT /books/0345391802 um ein Buch mit abzuspeichern
- DELETE /books/0345391802 löscht das Buch (z.B. wenn man es verschenkt)
- Zu jedem Buch sollte mindestens ISBN, Title und Autor gespeichert werden
- Speichere auch die Art Soft-Cover, E-Book, Hard-Cover, Hörbuch ( als enum)
- Nutze entweder eine class oder einen record

Mit Unit-Tests!
