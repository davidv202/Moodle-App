package com.example.MoodleApp.dtos;


import com.example.MoodleApp.enums.LECTURE_TYPE;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LectureDTO {
    private String cod;
    private String nume_disciplina;
    private LECTURE_TYPE tip_disciplina;
}
