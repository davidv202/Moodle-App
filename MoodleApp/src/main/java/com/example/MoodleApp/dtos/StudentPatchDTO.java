package com.example.MoodleApp.dtos;


import com.example.MoodleApp.enums.STUDY_CYCLE;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentPatchDTO {
    private String nume;
    private String prenume;
    private String email;
    private STUDY_CYCLE ciclu_studii;
    private Integer an_studiu;
    private Integer grupa;
}
