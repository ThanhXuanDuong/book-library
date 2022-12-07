package com.example.booklibrary;

import com.example.booklibrary.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepo repo;

    public List<Book> getAllBooks() {
        return repo.getBooks();
    }

    public Book getBookByISBN(String ISBN) {
        return repo.getBook(ISBN);
    }

    public Book putBook(Book book) {
        repo.putBook(book);
        return book;
    }

    public List<Book> deleteBookByISBN(String ISBN) {
        return repo.deleteBook(ISBN);
    }
}
