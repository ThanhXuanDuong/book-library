package com.example.booklibrary;

import com.example.booklibrary.model.Art;
import com.example.booklibrary.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Repository
public class BookRepo {
    List<Book> books=new ArrayList<>(List.of(
            new Book("123456","Basic Java","Tom", Art.E_BOOK),
            new Book("123344","Advanced Java","Max",Art.HARD_COVER)
    ));

    public Book getBook(String ISBN) {
        Book book=null;
        for (Book b: books){
            if (b.getISBN().equals(ISBN)){
                book=b;
                break;
            }
        }
        return book;
    }

    public List<Book> putBook(Book book) {
        try {
            books.set(books.indexOf(book), book);
        } catch (IndexOutOfBoundsException e){
            books.add(book);
        }
        return books;
    }

    public List<Book> deleteBook(String ISBN) {
        books.removeIf(book ->(book.getISBN().equals(ISBN)));
        return books;
    }
}
