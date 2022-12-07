package com.example.booklibrary;

import com.example.booklibrary.model.Art;
import com.example.booklibrary.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;
@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
class BookRepoTest {

    @Test
    void getAllBooks() {
        List<Book> books=new ArrayList<>(List.of(
                new Book("123456","Basic Java","Tom", Art.E_BOOK),
                new Book("123344","Advanced Java","Max",Art.HARD_COVER)
        ));
        BookRepo repo =new BookRepo(books);
        List<Book> actual = repo.getBooks();
        Assertions.assertEquals(List.of(
                new Book("123456","Basic Java","Tom",Art.E_BOOK),
                new Book("123344","Advanced Java","Max",Art.HARD_COVER)
        ),actual);
    }

    @Test
    void getBookById_WhenExists() {
        List<Book> books=new ArrayList<>(List.of(
                new Book("123456","Basic Java","Tom", Art.E_BOOK),
                new Book("123344","Advanced Java","Max",Art.HARD_COVER)
        ));
        BookRepo repo =new BookRepo(books);
        Book actual = repo.getBook("123344");
        Assertions.assertEquals(new Book("123344","Advanced Java","Max",Art.HARD_COVER),actual);
    }

    @Test
    void getBookById_WhenNotExist_ReturnNull() {
        List<Book> books=new ArrayList<>(List.of(
                new Book("123456","Basic Java","Tom", Art.E_BOOK),
                new Book("123344","Advanced Java","Max",Art.HARD_COVER)
        ));
        BookRepo repo =new BookRepo(books);
        Book actual = repo.getBook("121212");
        Assertions.assertNull(actual);
    }

    @Test
    void putBook_returnNewListBookAfterPutWhenNoSameBookInRepo() {
        List<Book> books=new ArrayList<>(List.of(
                new Book("123456","Basic Java","Tom", Art.E_BOOK),
                new Book("123344","Advanced Java","Max",Art.HARD_COVER)
        ));

        Book newBook= new Book("123123","Java","Bob",Art.E_BOOK);
        BookRepo repo =new BookRepo(books);
        List<Book> actual = repo.putBook(newBook);
        Assertions.assertEquals(List.of(
                new Book("123456","Basic Java","Tom",Art.E_BOOK),
                new Book("123344","Advanced Java","Max",Art.HARD_COVER),
                new Book("123123","Java","Bob",Art.E_BOOK)
        ),actual);
    }

    @Test
    void putBook_NotPutNewBookWhenSameBookInRepoExists() {
        List<Book> books=new ArrayList<>(List.of(
                new Book("123456","Basic Java","Tom",Art.E_BOOK),
                new Book("123344","Advanced Java","Max",Art.HARD_COVER)
        ));

        Book newBook=  new Book("123344","Advanced Java","Max",Art.HARD_COVER);
        BookRepo repo =new BookRepo(books);
        List<Book> actual = repo.putBook(newBook);
        Assertions.assertEquals(List.of(
                new Book("123456","Basic Java","Tom",Art.E_BOOK),
                new Book("123344","Advanced Java","Max",Art.HARD_COVER)
        ),actual);
    }

    @Test
    void deleteBook_WithValidISBN_returnListBookAfterDelete() {
        List<Book> books=new ArrayList<>(List.of(
                new Book("123456","Basic Java","Tom",Art.E_BOOK),
                new Book("123344","Advanced Java","Max",Art.HARD_COVER)
        ));

        BookRepo repo =new BookRepo(books);
        List<Book> actual = repo.deleteBook("123344");
        Assertions.assertEquals(List.of(
                new Book("123456","Basic Java","Tom",Art.E_BOOK)
        ),actual);
    }

    @Test
    void deleteBook_WithInvalidISBN_returnSameListBookAsBefore() {
        List<Book> books=new ArrayList<>(List.of(
                new Book("123456","Basic Java","Tom",Art.E_BOOK),
                new Book("123344","Advanced Java","Max",Art.HARD_COVER)
        ));

        BookRepo repo =new BookRepo(books);
        List<Book> actual = repo.deleteBook("123355");
        Assertions.assertEquals(List.of(
                new Book("123456","Basic Java","Tom",Art.E_BOOK),
                new Book("123344","Advanced Java","Max",Art.HARD_COVER)
        ),actual);
    }
}