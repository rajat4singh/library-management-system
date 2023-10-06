package com.library.management.system.controller;

import com.library.management.system.DTOs.requestDTOs.BookRequest;
import com.library.management.system.DTOs.responseDTOs.AuthorResponse;
import com.library.management.system.DTOs.responseDTOs.BookResponse;
import com.library.management.system.Enums.Genre;
import com.library.management.system.exception.AuthorNotFoundException;
import com.library.management.system.exception.BookNotFoundException;
import com.library.management.system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;


    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody BookRequest bookRequest ){

        try {
            String response = bookService.addBook(bookRequest);
            return new ResponseEntity(response, HttpStatus.CREATED);

        }
        catch(AuthorNotFoundException e){
            String response= e.getMessage();
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

    }

    // delete a book
    @DeleteMapping("/delete-bookid=/{id}")
    public ResponseEntity deleteBook(@PathVariable int id){
        try {
            String response = bookService.deleteBook(id);
            return new ResponseEntity(response, HttpStatus.CREATED);
        }
        catch (BookNotFoundException e){
            String response =e.getMessage();
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    // give me names of all the books of a particular genre
    @GetMapping("/get-by-genre")
    public ResponseEntity getBookByGenre(@RequestParam Genre genre){
        try{
            List<BookResponse> books= bookService.getBookByGenre(genre);
            return new ResponseEntity(books, HttpStatus.FOUND);
        }
        catch (BookNotFoundException e){
            String response= e.getMessage();
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }


    }

    // give me names of all the books of a particular genre and cost gretaer than x rs
    @GetMapping("/get-by-genre-and-cost-greater-than-x")
    public ResponseEntity GetBookByGenreAndCostMoreThanX(@RequestParam Genre genre, @RequestParam double x){

        try{
            List<BookResponse> books= bookService.GetBookByGenreAndCostMoreThanX(genre,x);
            return new ResponseEntity(books, HttpStatus.FOUND);
        }
        catch (BookNotFoundException e){
            String response= e.getMessage();
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
    }

    // give me all the books having number of pages between 'a' and 'b'
    @GetMapping("/all-books-having-pages-a-to-b")
    public ResponseEntity GetBooksHavingPagesBetweenAAndB(@RequestParam int a, @RequestParam int b){

        try{
            List<BookResponse> books= bookService.GetBooksHavingPagesBetweenAAndB(a, b);
            return new ResponseEntity(books, HttpStatus.FOUND);
        }
        catch (BookNotFoundException e){
            String response= e.getMessage();
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
    }

    // give me the names of all the authors who write a particular genre
    @GetMapping("/all-authors-with-genre=/{genre}")
    public ResponseEntity GetAllAuthorsWithGenreX(@PathVariable Genre genre){

        try{
            List<AuthorResponse> authors= bookService.GetAllAuthorsWithGenreX(genre);
            return new ResponseEntity(authors, HttpStatus.FOUND);
        }
        catch (AuthorNotFoundException e){
            String response= e.getMessage();
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
    }


}
