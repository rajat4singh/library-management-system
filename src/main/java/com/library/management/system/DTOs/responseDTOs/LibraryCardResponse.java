package com.library.management.system.DTOs.responseDTOs;

import com.library.management.system.Enums.CardStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LibraryCardResponse {

    String cardNo;

    CardStatus cardStatus;

    Date issueDate;

}
