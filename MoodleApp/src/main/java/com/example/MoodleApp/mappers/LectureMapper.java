package com.example.MoodleApp.mappers;

import com.example.MoodleApp.dtos.LectureDTO;
import com.example.MoodleApp.models.Lecture;
import org.springframework.stereotype.Component;

@Component
public class LectureMapper {

    public LectureDTO mapLectureToDTO(Lecture lecture) {
        LectureDTO lectureDTO = new LectureDTO();
        lectureDTO.setCod(lecture.getCod());
        lectureDTO.setNume_disciplina(lecture.getNume_disciplina());
        lectureDTO.setTip_disciplina(lecture.getTip_disciplina());
        return lectureDTO;
    }
}
