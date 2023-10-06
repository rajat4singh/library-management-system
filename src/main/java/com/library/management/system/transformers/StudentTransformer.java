package com.library.management.system.transformers;

import com.library.management.system.DTOs.requestDTOs.StudentRequest;
import com.library.management.system.DTOs.responseDTOs.LibraryCardResponse;
import com.library.management.system.DTOs.responseDTOs.StudentResponse;
import com.library.management.system.model.Student;

public class StudentTransformer {

    public static Student StudentRequestToStudent (StudentRequest studentRequest){
        return Student.builder()
                .name(studentRequest.getName())
                .email(studentRequest.getEmail())
                .age(studentRequest.getAge())
                .gender(studentRequest.getGender())
                .build();
    }

    public static StudentResponse StudentToStudentResponse(Student student){
        LibraryCardResponse libraryCardResponse=LibraryCardTransformer.StudentToLibraryCardResponse(student);
        return StudentResponse.builder()
                .name(student.getName())
                .email(student.getEmail())
                .libraryCardResponse(libraryCardResponse)
                .build();
    }
}
