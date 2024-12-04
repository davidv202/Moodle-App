package com.example.MoodleApp.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentDTO {
    private String nume;
    private String prenume;
    private List<LectureDTO> disciplineStudent;
}
