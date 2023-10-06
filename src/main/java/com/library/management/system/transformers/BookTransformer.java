package com.library.management.system.transformers;

import com.library.management.system.DTOs.requestDTOs.BookRequest;
import com.library.management.system.DTOs.responseDTOs.BookResponse;
import com.library.management.system.model.Book;

public class BookTransformer {
    public static Book BookRequestToBook(BookRequest bookRequest){
        return Book.builder()
                .title(bookRequest.getTitle())
                .noOfPages(bookRequest.getNoOfPages())
                .genre(bookRequest.getGenre())
                .cost(bookRequest.getCost())
                .build();
    }
    public static BookResponse BookToBookResponse(Book book){
        return BookResponse.builder()
                .title(book.getTitle())
                .genre(book.getGenre())
                .authorName(book.getAuthor().getName())
                .cost(book.getCost())
                .noOfPages(book.getNoOfPages())
                .build();
    }
}
