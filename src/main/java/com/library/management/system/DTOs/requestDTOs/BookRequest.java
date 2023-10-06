package com.library.management.system.DTOs.requestDTOs;

import com.library.management.system.Enums.Genre;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookRequest {

    String title;

    int noOfPages;

    Genre genre;

    Double cost;

    int authorId;
}
