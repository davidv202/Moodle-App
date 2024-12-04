package com.example.MoodleApp.mappers;

import com.example.MoodleApp.dtos.LectureDTO;
import com.example.MoodleApp.dtos.StudentDTO;
import com.example.MoodleApp.dtos.StudentPatchDTO;
import com.example.MoodleApp.models.Student;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class StudentMapper {

    public StudentDTO mapStudentToDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setNume(student.getNume());
        studentDTO.setPrenume(student.getPrenume());

        // Mapam disciplinele
        studentDTO.setDisciplineStudent(student.getDiscipline().stream()
                .map(lecture -> new LectureDTO(lecture.getCod(), lecture.getNume_disciplina(), lecture.getTip_disciplina()))
                .collect(Collectors.toList()));

        return studentDTO;
    }

    public StudentPatchDTO mapStudentPatchDTO(Student student) {
        StudentPatchDTO studentPatchDTO = new StudentPatchDTO();

        studentPatchDTO.setNume(student.getNume());
        studentPatchDTO.setPrenume(student.getPrenume());
        studentPatchDTO.setEmail(student.getEmail());
        studentPatchDTO.setCiclu_studii(student.getCiclu_studii());
        studentPatchDTO.setAn_studiu(student.getAn_studiu());
        studentPatchDTO.setGrupa(student.getGrupa());

        return studentPatchDTO;
    }
}
