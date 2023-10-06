package com.library.management.system.transformers;

import com.library.management.system.DTOs.requestDTOs.AuthorRequest;
import com.library.management.system.DTOs.responseDTOs.AuthorResponse;
import com.library.management.system.model.Author;

public class AuthorTransformer {

    public  static Author AuthorRequestToAuthor(AuthorRequest authorRequest){
        return Author.builder()
                .age(authorRequest.getAge())
                .email(authorRequest.getEmail())
                .name(authorRequest.getName())
                .build();
    }
    public static AuthorResponse AuthorToAuthorResponse(Author author){
        return AuthorResponse.builder()
                .name(author.getName())
                .email(author.getEmail())
                .build();
    }
}
