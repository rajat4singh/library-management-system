package com.library.management.system.DTOs.responseDTOs;

import com.library.management.system.Enums.Genre;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookResponse {

    String title;

    Genre genre;

    String authorName;

    int noOfPages;

    Double cost;

}
