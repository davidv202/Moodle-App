package com.example.MoodleApp.dtos;

import com.example.MoodleApp.enums.ACADEMIC_GRADE;
import com.example.MoodleApp.enums.ASSOCIATION_TYPE;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfessorDTO {
    private Long id;
    private String nume;
    private String prenume;
    private ASSOCIATION_TYPE tip_asociere;
    private ACADEMIC_GRADE grad_academic;
    private List<LectureDTO> disciplineProfesor;
}
