package com.library.management.system.DTOs.requestDTOs;

import com.library.management.system.Enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StudentRequest {

    String name;

    int age;

    String email;

    Gender gender;
}
