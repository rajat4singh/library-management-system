package com.library.management.system.transformers;

import com.library.management.system.DTOs.responseDTOs.LibraryCardResponse;
import com.library.management.system.Enums.CardStatus;
import com.library.management.system.model.LibraryCard;
import com.library.management.system.model.Student;

import java.util.UUID;

public class LibraryCardTransformer {

    public static LibraryCard PrepareLibraryCard(){
        return LibraryCard.builder()
                .cardStatus(CardStatus.ACTIVE)
                .CardId(UUID.randomUUID().toString())
                .build();
    }
    public static LibraryCardResponse StudentToLibraryCardResponse(Student student){
        return LibraryCardResponse.builder()
                .cardNo(student.getLibraryCard().getCardId())
                .cardStatus(student.getLibraryCard().getCardStatus())
                .issueDate(student.getLibraryCard().getDateOfIssue())
                .build();
    }
}
