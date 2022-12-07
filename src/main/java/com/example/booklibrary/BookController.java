package com.example.booklibrary;

import com.example.booklibrary.model.Book;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return service.getAllBooks();
    }

    @GetMapping( "/{ISBN}")
    public Book getBookByISBN(@PathVariable String ISBN){
        return service.getBookByISBN(ISBN);
    }

    @PutMapping("/{ISBN}")
    public Book putBookByISBN(@RequestBody Book book){
        return service.putBook(book);
    }

    @DeleteMapping("/{ISBN}")
    public List<Book> deleteBookByISBN(@PathVariable String ISBN){
        return service.deleteBookByISBN(ISBN);
    }
}
