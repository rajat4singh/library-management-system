package com.library.management.system.service;

import com.library.management.system.DTOs.requestDTOs.BookRequest;
import com.library.management.system.DTOs.responseDTOs.AuthorResponse;
import com.library.management.system.DTOs.responseDTOs.BookResponse;
import com.library.management.system.Enums.Genre;
import com.library.management.system.exception.AuthorNotFoundException;
import com.library.management.system.exception.BookNotFoundException;
import com.library.management.system.model.Author;
import com.library.management.system.model.Book;
import com.library.management.system.repository.AuthorRepository;
import com.library.management.system.repository.BookRepository;
import com.library.management.system.transformers.AuthorTransformer;
import com.library.management.system.transformers.BookTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;



    public String addBook(BookRequest bookRequest) {
        Optional<Author> authorOptional=authorRepository.findById(bookRequest.getAuthorId());
        if(authorOptional.isEmpty()){
            throw new AuthorNotFoundException("Invalid Author Id!");
        }
        Author author= authorOptional.get();
        Book book= BookTransformer.BookRequestToBook(bookRequest);
        book.setAuthor(author);
        author.getBooks().add(book);

        authorRepository.save(author);   // ths will save both book and author as it is cascaded
        return "Book Saved Successfully!";
    }

    public String deleteBook(int id) {
        Optional<Book> bookOptional= bookRepository.findById(id);
        if(bookOptional.isEmpty()){
            throw new BookNotFoundException("Invalid Book Id!");
        }
        bookRepository.deleteById(id);
        return "Book Deleted Successfully!";
    }

    public List<BookResponse> getBookByGenre(Genre genre) {
        List<Book> books=bookRepository.findByGenre(genre);
        List<BookResponse> bookResponses=new ArrayList<>();
        for(Book book: books){
            bookResponses.add(BookTransformer.BookToBookResponse(book));
        }
        if(bookResponses.isEmpty()){
            throw new BookNotFoundException("No books available of required genre!");
        }
        return bookResponses;
    }

    public List<BookResponse> GetBookByGenreAndCostMoreThanX(Genre genre, double x) {
        List<Book> books=bookRepository.findBookByGenreAndCostMoreThanX(genre, x);
        List<BookResponse> bookResponses=new ArrayList<>();
        for(Book book: books){
            bookResponses.add(BookTransformer.BookToBookResponse(book));
        }
        if(bookResponses.isEmpty()){
            throw new BookNotFoundException("No books available of required type!");
        }
        return bookResponses;
    }

    public List<BookResponse> GetBooksHavingPagesBetweenAAndB(int a, int b) {
        List<Book> books=bookRepository.findBooksHavingPagesBetweenAAndB(a, b);
        List<BookResponse> bookResponses=new ArrayList<>();
        for(Book book: books){
            bookResponses.add(BookTransformer.BookToBookResponse(book));
        }
        if(bookResponses.isEmpty()){
            throw new BookNotFoundException("No books available of required type!");
        }
        return bookResponses;
    }

    public List<AuthorResponse> GetAllAuthorsWithGenreX(Genre genre) {
        List<Author> authors=bookRepository.findAllAuthorsWithGenreX(genre);
        List<AuthorResponse> authorResponses= new ArrayList<>();
        for (Author author: authors){
            authorResponses.add(AuthorTransformer.AuthorToAuthorResponse(author));
        }
        if (authorResponses.isEmpty()){
            throw new AuthorNotFoundException("No author present with specific genre!");
        }
        return authorResponses;
    }
}
