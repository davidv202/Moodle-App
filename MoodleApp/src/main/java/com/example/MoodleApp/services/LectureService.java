package com.example.MoodleApp.services;


import com.example.MoodleApp.dtos.LectureDTO;
import com.example.MoodleApp.mappers.LectureMapper;
import com.example.MoodleApp.models.Lecture;
import com.example.MoodleApp.notification.NotificationService;
import com.example.MoodleApp.repositories.LectureRepository;
import com.example.MoodleApp.specifications.DynamicSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LectureService {

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private LectureMapper lectureMapper;

    // Functie paginare discipline
    public Page<LectureDTO> getLecturesPage(Map<String, Object> filters, Pageable pageable) {

        DynamicSpecificationBuilder<Lecture> specificationBuilder = new DynamicSpecificationBuilder<>();
        Specification<Lecture> specification = specificationBuilder.buildSpecification(filters);

        return lectureRepository.findAll(specification, pageable)
                .map(lectureMapper::mapLectureToDTO);
    }

    public Optional<LectureDTO> getLectureByCode(String code) {
        return lectureRepository.findById(code)
                .map(lectureMapper::mapLectureToDTO);
    }

    public Lecture createLecture(Lecture lecture) {

        notificationService.notifyProfessorForCreate();
        return lectureRepository.save(lecture);
    }

    public Lecture updateLecture(String code, Lecture lectureDetails) {
        Lecture lecture = lectureRepository.findById(code)
                .orElseThrow(() -> new RuntimeException("Lecture not found"));

        if(lectureDetails.getTitular() != null) {
            lecture.setTitular(lectureDetails.getTitular());
        }

        lecture.setNume_disciplina(lectureDetails.getNume_disciplina());
        lecture.setAn_studiu(lectureDetails.getAn_studiu());
        lecture.setTip_disciplina(lectureDetails.getTip_disciplina());
        lecture.setCategorie_disciplina(lectureDetails.getCategorie_disciplina());
        lecture.setTip_examinare(lectureDetails.getTip_examinare());

        return lectureRepository.save(lecture);
    }

    public void deleteLecture(String code) {
        lectureRepository.deleteById(code);
    }

}
