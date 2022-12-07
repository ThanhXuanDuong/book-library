package com.example.booklibrary;

import com.example.booklibrary.model.Art;
import com.example.booklibrary.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.mockito.Mockito.*;
@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
class BookServiceTest {

    @Test
    void getAllBooks() {
        //given
        List<Book> books=List.of(
                new Book("123456","Basic Java","Tom", Art.E_BOOK),
                new Book("123344","Advanced Java","Max",Art.HARD_COVER)
        );
        BookRepo repo= mock(BookRepo.class);
        //when
        when(repo.getBooks()).thenReturn(books);
        BookService service= new BookService(repo);
        List <Book> actual= service.getAllBooks();
        //then
        Assertions.assertEquals(books,actual);
        verify(repo).getBooks();
    }

    @Test
    void getBookByISBN_returnBookWhenISBNExist() {
        //given
        BookRepo repo= mock(BookRepo.class);
        //when
        when(repo.getBook("123456")).thenReturn(new Book("123456","Basic Java","Tom",Art.E_BOOK));
        BookService service= new BookService(repo);
        Book actual= service.getBookByISBN("123456");
        //then
        Assertions.assertEquals(new Book("123456","Basic Java","Tom",Art.E_BOOK),actual);
        verify(repo).getBook("123456");
    }

    @Test
    void getBookByISBN_returnNullWhenISBNNotExist() {
        //given
        List<Book> books=List.of(
                new Book("123456","Basic Java","Tom",Art.E_BOOK),
                new Book("123344","Advanced Java","Max",Art.HARD_COVER)
        );
        BookRepo repo= mock(BookRepo.class);
        //when
        when(repo.getBooks()).thenReturn(books);
        BookService service= new BookService(repo);
        Book actual= service.getBookByISBN("121212");
        //then
        Assertions.assertNull(actual);
        verify(repo).getBook("121212");
    }


    @Test
    void deleteBookByISBN_returnListAfterDeleteWhenISBNExist() {
        //given
        BookRepo repo= mock(BookRepo.class);
        //when
        when(repo.deleteBook("123344")).thenReturn(List.of(new Book("123456","Basic Java","Tom",Art.E_BOOK)));
        BookService service= new BookService(repo);
        List<Book>  actual= service.deleteBookByISBN("123344");
        //then
        Assertions.assertEquals(List.of(new Book("123456","Basic Java","Tom",Art.E_BOOK)),actual);
        verify(repo).deleteBook("123344");
    }

    @Test
    void deleteBookByISBN_returnSameListWhenISBNNotExist() {
        //given
        List<Book> books=List.of(
                new Book("123456","Basic Java","Tom",Art.E_BOOK),
                new Book("123344","Advanced Java","Max",Art.HARD_COVER)
        );
        BookRepo repo= mock(BookRepo.class);
        //when
        when(repo.deleteBook("123355")).thenReturn(books);
        BookService service= new BookService(repo);
        List<Book>  actual= service.deleteBookByISBN("123355");
        //then
        Assertions.assertEquals(books,actual);
        verify(repo).deleteBook("123355");
    }


}