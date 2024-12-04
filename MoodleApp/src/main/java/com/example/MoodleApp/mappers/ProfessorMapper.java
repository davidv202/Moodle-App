package com.example.MoodleApp.mappers;

import com.example.MoodleApp.dtos.LectureDTO;
import com.example.MoodleApp.dtos.ProfessorDTO;
import com.example.MoodleApp.models.Professor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProfessorMapper {

    public ProfessorDTO mapProfessorToDTO(Professor professor) {
        ProfessorDTO professorDTO = new ProfessorDTO();

        professorDTO.setId(professor.getId());
        professorDTO.setNume(professor.getNume());
        professorDTO.setPrenume(professor.getPrenume());
        professorDTO.setGrad_academic(professor.getGrad_didactic());
        professorDTO.setTip_asociere(professor.getTip_asociere());

        professorDTO.setDisciplineProfesor(professor.getDiscipline().stream()
                .map(lecture -> new LectureDTO(lecture.getCod(), lecture.getNume_disciplina(), lecture.getTip_disciplina()))
                .collect(Collectors.toList()));

        return professorDTO;
    }
}
