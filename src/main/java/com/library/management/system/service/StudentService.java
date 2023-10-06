package com.library.management.system.service;

import com.library.management.system.DTOs.requestDTOs.StudentRequest;
import com.library.management.system.DTOs.responseDTOs.StudentResponse;
import com.library.management.system.Enums.Gender;
import com.library.management.system.exception.StudentNotFoundException;
import com.library.management.system.model.LibraryCard;
import com.library.management.system.model.Student;
import com.library.management.system.repository.StudentRepository;
import com.library.management.system.transformers.LibraryCardTransformer;
import com.library.management.system.transformers.StudentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    public StudentResponse addStudent(StudentRequest studentRequest) {


//        LibraryCard libraryCard= new LibraryCard();
//        libraryCard.setCardId(String.valueOf(UUID.randomUUID()));
//        libraryCard.setCardStatus(CardStatus.ACTIVE);
//        libraryCard.setStudent(student);
//        student.setLibraryCard(libraryCard);
//        Student savedStudent = studentRepository.save(student);

        Student student= StudentTransformer.StudentRequestToStudent(studentRequest);

        LibraryCard libraryCard= LibraryCardTransformer.PrepareLibraryCard();
        libraryCard.setStudent(student);
        student.setLibraryCard(libraryCard);

        Student savedStudent = studentRepository.save(student);

        return StudentTransformer.StudentToStudentResponse(savedStudent);

    }

    public StudentResponse getStudent(int regNo) {
        Optional<Student> studentOptional= studentRepository.findById(regNo);
        if(studentOptional.isEmpty()){
            throw new StudentNotFoundException("Invalid Registration Id!");
        }
        Student student=studentOptional.get();
        return StudentTransformer.StudentToStudentResponse(student);
    }

    public String deleteByRegNo(int regNo) {
        boolean isPresent= studentRepository.existsById(regNo);
        if(!isPresent){
            throw new StudentNotFoundException("Invalid Student Id!");
        }
        studentRepository.deleteById(regNo);
        return "Student Deleted Successfully";
    }

    public String updateAge(int regNo, int newAge) {
        Optional<Student> studentOptional= studentRepository.findById(regNo);
        if(studentOptional.isEmpty()){
            throw new StudentNotFoundException("Invalid Registration Number!");
        }
        Student student= studentOptional.get();
        student.setAge(newAge);
        studentRepository.save(student);
        return "Age Updated Successfully!";
    }

    public List<StudentResponse> getAllStudent() {
        List<Student> students= studentRepository.findAll();
        List<StudentResponse> studentResponses= new ArrayList<>();
        for (Student student:students){
            studentResponses.add(StudentTransformer.StudentToStudentResponse(student));
        }
        return studentResponses;
    }

    public List<StudentResponse> getAllMaleStudent() {
        List<Student> students= studentRepository.findByGender(Gender.MALE);
        if (students.isEmpty()){
            throw new StudentNotFoundException("No MALE student found!");
        }
        List<StudentResponse> studentResponses= new ArrayList<>();
        for (Student student:students){
            studentResponses.add(StudentTransformer.StudentToStudentResponse(student));
        }
        return studentResponses;

    }
}
